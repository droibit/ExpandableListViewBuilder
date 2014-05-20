/**
 * 
 */
package com.droibit.expandablelistviewbuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.droibit.collection.KeyValuePair;

/**
 *
 *
 * @author kumagai
 *
 */
public class GroupedList<K, V> {

	/** 親グループ名を取得するためのキー */
	static String GROUP_KEY = "Group";

	/** 1番めの子項目を取得するためのキー */
	static String CHILD_KEY_1 = "Child1";
	
	/** 1番めの子項目を取得するためのキー */
	static String CHILD_KEY_2 = "Child2";

	/** 親グループの名のリスト*/
	List<Map<String, K>> mGroupData;
	
	/** 親グループに対応する子項目のリスト */
	List<List<Map<String, V>>> mChildData;
	
	/**
	 * 新しいインスタンスを作成する
	 */
	public GroupedList() {
		mGroupData = new ArrayList<Map<String, K>>();
		mChildData = new ArrayList<List<Map<String, V>>>();
	}
					
	/**
	 * 子項目が単一の文字列の新しいグループを作成する。
	 * 
	 * @param srcGroup ソースグループ
	 */
	public void newGroupsOne(Map<K, List<V>> srcGroup) {
		if (srcGroup == null) {
			return;
		}
		
		for (K key : srcGroup.keySet()) {
	        mGroupData.add(newGroup(key));
	
	        // 子アイテムリストを作成する
	        final List<V> items = srcGroup.get(key);
	        final List<Map<String, V>> children = new ArrayList<Map<String, V>>(items.size());
	        for (V item : items) {
	            final Map<String, V> child = new HashMap<String, V>(1);
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
	public void newGroupOne(K group, List<V> children) {
		if (group == null || getGroupIndex(group) != -1) {
			return;
		}
		
		final Map<K, List<V>> srcGroup = new HashMap<K, List<V>>(1);
		srcGroup.put(group, children);
		newGroupsOne(srcGroup);
	}
	
	/**
	 * 子項目が2行の文字列の新しいグループを作成する。
	 * 
	 * @param srcGroup ソースグループ
	 */
	public void newGroupTwo(Map<K, List<KeyValuePair<V, V>>> srcGroup) {
		if (srcGroup == null) {
			return;
		}
		
		for (K key : srcGroup.keySet()) {
			mGroupData.add(newGroup(key));
			
			// 子アイテムリストを作成する
	        final List<KeyValuePair<V, V>> items = srcGroup.get(key);
	        final List<Map<String, V>> children = new ArrayList<Map<String, V>>(items.size());
	        for (KeyValuePair<V, V> item : items) {
	            final Map<String, V> child = new HashMap<String, V>(2);
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
	public void newGroupTwo(K group, List<KeyValuePair<V, V>> children) {
		if (group == null || children == null || getGroupIndex(group) != -1) {
			return;
		}
		
		final Map<K, List<KeyValuePair<V, V>>> srcGroup = new HashMap<K, List<KeyValuePair<V, V>>>();
		srcGroup.put(group, children);
		newGroupTwo(srcGroup);
	}
	
	/**
	 * 対象のグループに子項目を追加する
	 * 
	 * @param group グループ名
	 * @param child 子項目
	 */
	public void addChild(K group, V child) {
		final int index = getGroupIndex(group);
		if (index == -1 || child == null) {
			return;
		}
		
		final List<Map<String, V>> children = mChildData.get(index);
		for (Map<String, V> childData : children) {
			if (childData.values().contains(child)) {
				return;
			}
		}
		
		final Map<String, V> childData = new HashMap<String, V>(1);
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
	public void addChild(K group, V child1, V child2) {
		final int index = getGroupIndex(group);
		if (index == -1 || child1 == null || child2 == null) {
			return;
		}
		
		final List<Map<String, V>> children = mChildData.get(index);
		for (Map<String, V> _child : children) {
			if (_child.values().contains(child1)) {
				return;
			}
		}
		
		final Map<String, V> childData = new HashMap<String, V>(2);
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
	public boolean contains(K group) {
		return getGroupIndex(group) != -1;
	}
	
	/**
	 * グループ名のリストを取得する
	 * 
	 * @return グループ名のリスト
	 */
	public List<K> getGroups() {
		if (mGroupData.isEmpty()) {
			return null;
		}
		
		final List<K> groups = new ArrayList<K>(mGroupData.size());
		for (Map<String, K> group : mGroupData) {
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
	public List<V> getChildrenOne(K group) {
		final int index = getGroupIndex(group);
		if (index == -1) {
			return null;
		}

		final List<V> children = new ArrayList<V>();
		for (Map<String, V> child : mChildData.get(index)) {
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
	public List<KeyValuePair<V, V>> getChildrenTwo(K group) {
		int index = getGroupIndex(group);
		if (index == -1) {
			return null;
		}

		final List<KeyValuePair<V, V>> children = new ArrayList<KeyValuePair<V, V>>();
		for (Map<String, V> child : mChildData.get(index)) {
			if (child.containsKey(CHILD_KEY_1) && child.containsKey(CHILD_KEY_2)) {
				children.add(new KeyValuePair<V, V>(child.get(CHILD_KEY_1), child.get(CHILD_KEY_2)));
			}
		}
		return children;
	}
	
	private Map<String, K> newGroup(K groupName) {
		final Map<String, K> newGroup = new HashMap<String, K>(1);
        newGroup.put(GROUP_KEY, groupName);
        
        return newGroup;
	}
	
	private int getGroupIndex(K group) {
		for (int i = 0; i < mGroupData.size(); i++) {
			final Map<String, K> groupData = mGroupData.get(i);
			if (groupData.containsValue(group)) {
				return i;
			}
		}
		return -1;
	}
}
