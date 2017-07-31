package com.company;

//Pavlo Khryshcheniuk
//package com.company;

import java.util.Scanner;

class Node {

    char data;
    Node right;
    Node left;

    Node(char data) {
        this.data = data;
        right = null;
        left = null;

    }

}

class Tree {
    Node root;
    String lex1, lex2;
    long height;
    long numberofnodes;
    long perms;
    String str = "";

    Tree(char rootlabel) {
        root = new Node(rootlabel);
        height = 1;
        numberofnodes = 1;
        lex1 = "";
        lex2 = "";
        str += rootlabel;
    }

    public void insert(char ch) {
        Node node = new Node(ch);
        Node prev = null;
        Node temp = root;
        while (temp != null && temp.data != node.data) {
            if (temp.data < node.data) {
                prev = temp;
                temp = temp.right;

            } else if (temp.data > node.data) {
                prev = temp;
                temp = temp.left;
            }


        }

        if (temp == null) {


            if (node.data > prev.data) {
                prev.right = node;
                numberofnodes++;
                str += node.data;
            } else if (node.data < prev.data) {
                prev.left = node;
                numberofnodes++;
                str += node.data;
            }

        }
    }

    public long max(long x, long y) {
        if (x > y) return x;
        else return y;
    }

    public long findHeight(Node node) {
        if (node == null) return 0;
        return 1 + max(findHeight(node.left), findHeight(node.right));
    }


    public void postorder(Node node) {
        if (node == null) {
            return;
        }

        lex1 += node.data;
        postorder(node.left);
        postorder(node.right);
    }

    public void preorder(Node node) {
        if (node == null) {
            return;
        }
        preorder(node.left);
        preorder(node.right);
        lex2 += node.data;

    }

    public static double f(int x, int y) {
        if (y < 0 || y > x) return 0;
        if (y > x / 2) {

            y = x - y;
        }
        double answer = 1;
        for (int i = 1; i <= y; i++) {
            answer *= (x + 1 - i);
            answer /= i;
        }
        return answer;
    }


    public double permutations(String str) {
        if (str.length() <= 1) return 1;
        String left = "", right = "";
        for (int i = 1; i < str.length(); i++) {

            if (str.charAt(i) < str.charAt(0))

                left += str.charAt(i);
            else
                right += str.charAt(i);
        }
        return permutations(left) * permutations(right) * f(left.length() + right.length(), right.length());
    }
}


public class Main {

    public static void main(String[] args) {
        Scanner inScan = new Scanner(System.in);

        int z = inScan.nextInt();
        inScan.nextLine();


        while (z-- > 0) {
            String line = inScan.nextLine();
            String str = "";
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) <= 'Z' && line.charAt(i) >= 'A') {
                    str += line.charAt(i);
                }
            }
            Tree bst = new Tree(str.charAt(0));
            for (int i = 1; i < str.length(); i++) {
                bst.insert(str.charAt(i));

            }

            bst.postorder(bst.root);
            bst.preorder(bst.root);
            bst.lex2 = new StringBuilder(bst.lex2).reverse().toString();
            System.out.print(bst.numberofnodes + ",");
            System.out.print(bst.findHeight(bst.root) - 1 + ",");
            System.out.println((long) bst.permutations(bst.str) + "," + bst.lex1 + "," + bst.lex2);

        }


    }
}
/*
8
BOGACZ
LAMPA
PALMA
PLAMA
BAOBAB
B.A=OxB?A+B2015
MAGISTER
INFORMATYK
*/
