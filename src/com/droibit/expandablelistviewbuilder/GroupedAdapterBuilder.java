/**
 * 
 */
package com.droibit.expandablelistviewbuilder;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

/**
 * {@link SimpleExpandableListAdapter}を作成するためのユーティリティクラス。
 *
 * @author kumagai
 *
 */
public final class GroupedAdapterBuilder {
	
	private GroupedAdapterBuilder() {
	}

	/**
	 * 小項目が{@link android.R.layout.simple_expandable_list_item_1} の {@link SimpleExpandableListAdapter}を作成する。
	 * 
	 * @param groupedList グルーピングしたリスト
	 * @return 新しい{@link SimpleExpandableListAdapter}オブジェクト
	 */
	public static final SimpleExpandableListAdapter fromOne(Context context, GroupedStringList groupedList) {
		return new SimpleExpandableListAdapter(
                context,
                groupedList.mGroupData,
                android.R.layout.simple_expandable_list_item_1,
                new String [] {GroupedStringList.GROUP_KEY},
                new int [] {android.R.id.text1},
                groupedList.mChildData,
                android.R.layout.simple_expandable_list_item_1,
                new String [] {GroupedStringList.CHILD_KEY_1},
                new int [] {android.R.id.text1}
        );
	}
	
	/**
	 * 小項目が{@link android.R.layout.simple_expandable_list_item_2} の {@link SimpleExpandableListAdapter}を作成する。
	 * 
	 * @param groupedList グルーピングしたリスト
	 * @return 新しい{@link SimpleExpandableListAdapter}オブジェクト
	 */
	public static final SimpleExpandableListAdapter fromTwo(Context context, GroupedStringList groupedList) {
		return new SimpleExpandableListAdapter(
                context,
                groupedList.mGroupData,
                android.R.layout.simple_expandable_list_item_1,
                new String [] {GroupedStringList.GROUP_KEY},
                new int [] {android.R.id.text1},
                groupedList.mChildData,
                android.R.layout.simple_expandable_list_item_2,
                new String [] {GroupedStringList.CHILD_KEY_1, GroupedStringList.CHILD_KEY_2},
                new int [] {android.R.id.text1, android.R.id.text2}
        );		
	}
}
