package reco.frame.tv;

import android.app.Activity;

/**
 * Copyright (c) 2012-2013, Michael Yang  (www.yangfuhai.com).
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http:www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class TvActivity extends Activity {

//	/**
//	 * 全局遥控 false 则只对当前activity有效
//	 */
//	private boolean global=true;
//	
//
//	public void setContentView(int layoutResID) {
//		super.setContentView(layoutResID);
//		init();
//	}
//
//
//	public void setContentView(View view, LayoutParams params) {
//		super.setContentView(view, params);
//		init();
//	}
//
//
//	public void setContentView(View view) {
//		super.setContentView(view);
//		init();
//	}
//	
//	/**
//	 * 初始化 启动服务或线程
//	 * @param activity
//	 */
//	public void init(){
//		
//		TvRemoteSocket remote=new TvRemoteSocket();
//		remote.setListener(new RemoteListener() {
//			
//			@Override
//			public Object OnRemoteReceive(String action,Object file) {
//				return TvActivity.this.OnRemoteFileReceive(file);
//			}
//			
////			@Override
////			public void OnRemoteEvent(int event) {
////				TvActivity.this.OnRemoteEvent(event);
////				
////			}
//		});
//		remote.startRemoteServer();
//	}
//	
//	
//	/**
//	 * 收到遥控传来文件
//	 */
//	public Object OnRemoteFileReceive(Object file){
//		return null;
//	}
//	/**
//	 * 收到遥控事件
//	 */
//	public void OnRemoteEvent(int event) {
//		
//	}
//	
//	
//	
//	public enum Method{
//		Click,LongClick,ItemClick,itemLongClick
//	}
//	
//	

}

