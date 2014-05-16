## ExpandableListViewBuilder
`SimpleExpandableListAdapter` を簡単に作成するためのライブラリ。
アダプタを作成する際の面倒なコレクションの管理を行います。

## Usage
`SimpleExpandableListAdapter` を作成する際は次のようになります。

``` java
// 子項目が1つの場合
List<String> languages = new ArrayList<String>();
languages.add("Java");
languages.add("C#");
languages.add("Python");

GroupedStringList groupedList1 = new GroupedStringList();
groupedList1.newGroupeOne("Programming", languages);
SimpleExpandableListAdapter adapter1 = GroupedAdapterBuilder.fromOne(groupedList1);

// 子項目が2つの場合
List<StringPair> companies = new ArrayList<StringPair>();
companies.add(new StringPair("Google", "1998/9/4"));
companies.add(new StringPair("Microsoft", "1975/4/4"));
companies.add(new StringPair("Apple", "1976/4/1"));

GroupedStringList groupedList2 = new GroupedStringList();
groupedList1.newGroupeOne("Company", companies);
SimpleExpandableListAdapter adapter2 = GroupedAdapterBuilder.fromOne(groupedList1);
```

## License

    Copyright 2014 droibit

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
