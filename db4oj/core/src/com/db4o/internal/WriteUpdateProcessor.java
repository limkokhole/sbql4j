/* Copyright (C) 2009  Versant Inc.  http://www.db4o.com */

package com.db4o.internal;

import com.db4o.*;
import com.db4o.foundation.*;
import com.db4o.internal.delete.*;
import com.db4o.internal.marshall.*;
import com.db4o.internal.slots.*;

/**
 * @exclude
 */
class WriteUpdateProcessor {
	
	private final LocalTransaction _transaction;

	private final int _id;
	
	private final ClassMetadata _clazz;
	
	private final ArrayType _typeInfo;
	
	private int _cascade = 0;

	public WriteUpdateProcessor(LocalTransaction transaction, 
			int id, 
			ClassMetadata clazz, 
			ArrayType typeInfo) {
		_transaction = transaction;
		_id = id;
		_clazz = clazz;
		_typeInfo = typeInfo;
	}

	public void run(){
    	_transaction.checkSynchronization();
    	
        if(DTrace.enabled){
            DTrace.WRITE_UPDATE_ADJUST_INDEXES.log(_id);
        }
        
        if(alreadyHandled()){
        	return;
        }
        
        // TODO: Try to get rid of getting the slot here because it 
        //       will invoke reading a pointer from the file system.
        //       It may be possible to figure out the readd case
        //       by asking the IdSystem in a smarter way.
        Slot slot = _transaction.idSystem().currentSlot(_id);
        if(handledAsReAdd(slot)){
        	return;
        }
        
        if(_clazz.canUpdateFast()){
        	return;
        }
        
        StatefulBuffer objectBytes = container().readStatefulBufferBySlot(_transaction, _id, slot);
        
        deleteMembers(objectBytes);
	}

	private LocalObjectContainer container() {
		return _transaction.localContainer();
	}

	private void deleteMembers(StatefulBuffer objectBytes) {
		ObjectHeader oh = new ObjectHeader(_clazz, objectBytes);
        
        DeleteInfo info = (DeleteInfo)TreeInt.find(_transaction._delete, _id);
        if(info != null){
            if(info._cascade > _cascade){
                _cascade = info._cascade;
            }
        }
        
        objectBytes.setCascadeDeletes(_cascade);
        
        DeleteContextImpl context = new DeleteContextImpl(objectBytes, oh, _clazz.classReflector(), null);
        _clazz.deleteMembers(context, _typeInfo, true);
	}

	private boolean handledAsReAdd(Slot slot) {
		if(! Slot.isNull(slot)){
			return false;
		}
        _clazz.addToIndex(_transaction, _id);
        return true;
	}
	
	private boolean alreadyHandled() {
		TreeInt newNode = new TreeInt(_id);
        _transaction._writtenUpdateAdjustedIndexes = Tree.add(_transaction._writtenUpdateAdjustedIndexes, newNode);
        return ! newNode.wasAddedToTree();
	}

}