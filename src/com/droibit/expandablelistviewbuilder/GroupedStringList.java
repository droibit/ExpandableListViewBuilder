/**
 * 
 */
package com.droibit.expandablelistviewbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.text.TextUtils;
import android.widget.ExpandableListView;

import com.droibit.collection.StringPair;

/**
 * {@link ExpandableListView}で表示する文字情報を格納するリスト。
 * 子項目は1行もしくは2行の情報を持つことができる。
 *
 * @author kumagai
 *
 */
public class GroupedStringList {
	
	/** 親グループ名を取得するためのキー */
	static String GROUP_KEY = "Group";

	/** 1番めの子項目を取得するためのキー */
	static String CHILD_KEY_1 = "Child1";
	
	/** 1番めの子項目を取得するためのキー */
	static String CHILD_KEY_2 = "Child2";
	
	/** 親グループの名のリスト*/
	List<Map<String, String>> mGroupData;
	
	/** 親グループに対応する子項目のリスト */
	List<List<Map<String, String>>> mChildData;
	
	/**
	 * 新しいインスタンスを作成する
	 */
	public GroupedStringList() {
		mGroupData = new ArrayList<Map<String,String>>();
		mChildData = new ArrayList<List<Map<String,String>>>();
	}
					
	/**
	 * 子項目が単一の文字列の新しいグループを作成する。
	 * 
	 * @param srcGroup ソースグループ
	 */
	public void newGroupsOne(Map<String, List<String>> srcGroup) {
		if (srcGroup == null) {
			return;
		}
		
		for (String key : srcGroup.keySet()) {
	        mGroupData.add(newGroup(key));
	
	        // 子アイテムリストを作成する
	        final List<String> items = srcGroup.get(key);
	        final List<Map<String, String>> children = new ArrayList<Map<String, String>>(items.size());
	        for (String item : items) {
	            final Map<String, String> child = new HashMap<String, String>(1);
	            child.put(CHILD_KEY_1, item);
	            children.add(child);
	        }
	        mChildData.add(children);
		}
	}
	
	/**
	 * 子項目が単一の文字列の新しいグループを作成する。
	 * 
	 * @param group グループ名
	 * @param children 子項目のリスト
	 */
	public void newGroupOne(String group, List<String> children) {
		if (TextUtils.isEmpty(group) || getGroupIndex(group) != -1) {
			return;
		}
		
		final Map<String, List<String>> srcGroup = new HashMap<String, List<String>>(1);
		srcGroup.put(group, children);
		newGroupsOne(srcGroup);
	}
	
	/**
	 * 子項目が2行の文字列の新しいグループを作成する。
	 * 
	 * @param srcGroup ソースグループ
	 */
	public void newGroupTwo(Map<String, List<StringPair>> srcGroup) {
		if (srcGroup == null) {
			return;
		}
		
		for (String key : srcGroup.keySet()) {
			mGroupData.add(newGroup(key));
			
			// 子アイテムリストを作成する
	        final List<StringPair> items = srcGroup.get(key);
	        final List<Map<String, String>> children = new ArrayList<Map<String, String>>(items.size());
	        for (StringPair item : items) {
	            final Map<String, String> child = new HashMap<String, String>(2);
	            child.put(CHILD_KEY_1, item.key);
	            child.put(CHILD_KEY_2, item.value);
	            children.add(child);
	        }
	        mChildData.add(children);
		}
	}
	
	/**
	 * 子項目が2行の文字列の新しいグループを作成する。
	 * 
	 * @param group グループ名
	 * @param children 子項目のリスト
	 */
	public void newGroupTwo(String group, List<StringPair> children) {
		if (TextUtils.isEmpty(group) || children == null || getGroupIndex(group) != -1) {
			return;
		}
		
		final Map<String, List<StringPair>> srcGroup = new HashMap<String, List<StringPair>>();
		srcGroup.put(group, children);
		newGroupTwo(srcGroup);
	}
	
	/**
	 * 対象のグループに子項目を追加する
	 * 
	 * @param group グループ名
	 * @param child 子項目
	 */
	public void addChild(String group, String child) {
		final int index = getGroupIndex(group);
		if (index == -1 || TextUtils.isEmpty(child)) {
			return;
		}
		
		final List<Map<String, String>> children = mChildData.get(index);
		for (Map<String, String> childData : children) {
			if (childData.values().contains(child)) {
				return;
			}
		}
		
		final Map<String, String> childData = new HashMap<String, String>(1);
		childData.put(CHILD_KEY_1, child);
		children.add(childData);
		
		mChildData.add(children);
	}
	
	/**
	 *  対象のグループに子項目を追加する。
	 *  
	 * @param group グループ名
	 * @param child1 子項目1
	 * @param child2 小項目2
	 */
	public void addChild(String group, String child1, String child2) {
		final int index = getGroupIndex(group);
		if (index == -1 || TextUtils.isEmpty(child1) || TextUtils.isEmpty(child2)) {
			return;
		}
		
		final List<Map<String, String>> children = mChildData.get(index);
		for (Map<String, String> _child : children) {
			if (_child.values().contains(child1)) {
				return;
			}
		}
		
		final Map<String, String> childData = new HashMap<String, String>(2);
		childData.put(CHILD_KEY_1, child1);
		childData.put(CHILD_KEY_2, child2);
		children.add(childData);
	}
	
	/**
	 * 既にグループが存在しているかどうか確認する。
	 * 
	 * @param group グループ名
	 * @return trueの場合存在している、falseの場合存在していない
	 */
	public boolean contains(String group) {
		return getGroupIndex(group) != -1;
	}
	
	/**
	 * グループ名のリストを取得する
	 * 
	 * @return グループ名のリスト
	 */
	public List<String> getGroups() {
		if (mGroupData.isEmpty()) {
			return null;
		}
		
		final List<String> groups = new ArrayList<String>(mGroupData.size());
		for (Map<String, String> group : mGroupData) {
			groups.add(group.get(GROUP_KEY));
		}
		return groups;
	}
	
	/**
	 * 対象のグループに対応する子項目リストを取得する
	 * 
	 * @param group グループ名
	 * @return 子項目リスト
	 */
	public List<String> getChildrenOne(String group) {
		int index = getGroupIndex(group);
		if (index == -1) {
			return null;
		}

		final List<String> children = new ArrayList<String>();
		for (Map<String, String> child : mChildData.get(index)) {
			if (child.containsKey(CHILD_KEY_1)) {
				children.add(child.get(CHILD_KEY_1));
			}
		}
		return children;
	}
	
	/**
	 * 対象のグループに対応する子項目リストを取得する
	 * 
	 * @param group グループ名
	 * @return 子項目リスト
	 */
	public List<StringPair> getChildrenTwo(String group) {
		int index = getGroupIndex(group);
		if (index == -1) {
			return null;
		}

		final List<StringPair> children = new ArrayList<StringPair>();
		for (Map<String, String> child : mChildData.get(index)) {
			if (child.containsKey(CHILD_KEY_1) && child.containsKey(CHILD_KEY_2)) {
				children.add(new StringPair(child.get(CHILD_KEY_1), child.get(CHILD_KEY_2)));
			}
		}
		return children;
	}
	
	private Map<String, String> newGroup(String groupName) {
		final Map<String, String> newGroup = new HashMap<String, String>(1);
        newGroup.put(GROUP_KEY, groupName);
        
        return newGroup;
	}
	
	private int getGroupIndex(String group) {
		for (int i = 0; i < mGroupData.size(); i++) {
			final Map<String, String> groupData = mGroupData.get(i);
			if (groupData.containsValue(group)) {
				return i;
			}
		}
		return -1;
	}
}
