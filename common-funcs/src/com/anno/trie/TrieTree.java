package com.anno.trie;

public class TrieTree {
    private TrieNode root;
    
    public TrieTree() {
        root = new TrieNode();
    }
    
    /*
        Params:
            strs(String[]): the array of string to compare
        Return:
            the final longest common prefix string
    */
    public String longestCommonPrefix(String[] strs) {
        for (String str: strs) {
            insert(str);
        }
        return getStringBeforeDivided(strs[0]);
    }
    
    /*
        Params:
            root(TrieNode): the root of trie tree
            str(String): the target string to insert into the trie tree
        Return:
            None
    */
    public void insert(String str) {
        TrieNode curNode = root;
        for (char curChar: str) {
            int idx = (int) curChar - 'a';
            System.out.println("idx: " + idx);
            if (curNode.children[idx] == null) {
                curNode.childNum++;
                curNode.children[idx] = new TrieNode();
                curNode = curNode.children[idx];
            }
        }
    }
    
    /*
        Params:
            root(TrieNode): the root of trie tree
            target(String): the target string to search the longest common prefix string in trie tree
        Return:
            str(String): the final longest common prefix string
    */
    public String getStringBeforeDivided(String target) {
        StringBuilder str = new StringBuilder();
        TrieNode curNode = root;
        for (char curChar: target) {
            int curCharIdx = (int) curChar - 'a';
            if (curNode.childNum == 1 && curNode.children[curCharIdx] != null) {
                curNode = curNode.children[curCharIdx];
                str.append(curChar);
            } else {
                break;
            }
        }
        return str.toString();
    }
}