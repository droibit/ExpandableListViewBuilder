/**
 * 
 */
package com.droibit.expandablelistviewbuilder;

import android.widget.ExpandableListView;

/**
 * {@link ExpandableListView}で表示する文字情報を格納するリストを作成するためのユーティリティクラス。<BR>
 * 子項目は1行もしくは2行の情報を持つことができる。
 *
 * @author kumagai
 *
 */
public final class GroupedStringLists  {
	
	/**
	 * グループ名が文字列のグループ化リストを取得する
	 * 
	 * @return グループ名が文字列のグループ化リスト
	 */
	public static <V> GroupedList<String, V> newParentStringList() {
		return new GroupedList<String, V>();
	}
	
	/**
	 * 文字列のグループ化リストを取得する
	 * 
	 * @return 文字列のグループ化リスト
	 */
	public static GroupedList<String, String> newStringList() {
		return new GroupedList<String, String>();
	}
	
	private GroupedStringLists() {
		
	}
}
