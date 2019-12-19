package com.zhbit.loader;

import java.io.File;

import android.content.Context;

/**
 * 慕课网学习 瀑布流加载图片 使用StaggeredGridView框架
 * 文件缓存
 * @author lenat
 *
 */

public class FileCache {

	private File cacheDir;
	public FileCache(Context context){
		if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
			cacheDir = new File(android.os.Environment.getExternalStorageDirectory(),"LazyList");
			else
				cacheDir = context.getCacheDir();
			if(!cacheDir.exists())
				cacheDir.mkdirs();
	}
	public File getFile(String url){
		String filename = String.valueOf(url.hashCode());
		File f = new File(cacheDir,filename);
		return f;
	}
	public void clear(){
		File[] files = cacheDir.listFiles();
		if(files == null)
			return;
		for(File f:files)
			f.delete();
	}
}
