package com.zhbit.loader;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.graphics.Bitmap;
import android.util.Log;


/**
 * 慕课网学习 瀑布流加载图片 使用StaggeredGridView框架
 * 内存缓存
 * @author lenat
 *
 */
public class MemoryCache {

	private Map<String,Bitmap> cache = Collections 
			.synchronizedMap(new LinkedHashMap<String,Bitmap>(10,1.5f,true));
	private long size = 0;
	private long limit = 100000;
	private static final String TAG = "CacheLog";
	
	public MemoryCache(){
		setLimit(Runtime.getRuntime().maxMemory()/4);
	}
	
	public void setLimit(long newLimit){
		limit = newLimit;
		Log.i(TAG,"MemoryCache will use up to "+limit/1024/1024+"MB");
	}
	
	public Bitmap get(String id){
		try{
			if(!cache.containsKey(id))
				return null;
			return cache.get(id);
		} catch(NullPointerException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public void put(String id, Bitmap bitmap){
		try{
			if(!cache.containsKey(id)){
				cache.put(id, bitmap);
				size += getSizeInBytes(bitmap);
				checkSize();
			}
		} catch(Throwable th){
			th.printStackTrace();
		}
	}
	
	// 检查内存
	private void checkSize(){
		Log.i(TAG,"cache size="+size+"length="+cache.size());
		if(size> limit){
			Iterator<Entry<String,Bitmap>> iter = cache.entrySet().iterator();
			while(iter.hasNext()){
				Entry<String,Bitmap> entry = iter.next();
				size -= getSizeInBytes(entry.getValue());
				iter.remove();
				if(size <= limit)
					break;
			}
			Log.i(TAG,"Clean cache New size"+cache.size());
		}
	}
	
	public void clear(){
		try{
			cache.clear();
			size = 0;
		} catch(NullPointerException ex){
			ex.printStackTrace();
		}
	}
	
	long getSizeInBytes(Bitmap bitmap){
		if(bitmap == null)
			return 0;
		return bitmap.getRowBytes()*bitmap.getHeight();	
	}
	
}
