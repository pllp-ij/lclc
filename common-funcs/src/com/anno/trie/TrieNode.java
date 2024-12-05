package com.anno.trie;

import java.util.ArrayList;

public class TrieNode {
    public TrieNode[] children;
    public int childNum;
    
    public TrieNode() {
        this.children = new TrieNode[26];
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
        this.childNum = 0;
    }
}