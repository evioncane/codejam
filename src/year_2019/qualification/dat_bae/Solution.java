package year_2019.qualification.dat_bae;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    private static List<Node> generateChildNodes(List<Node> parentNoodes){
        List<Node> childNodeList = new ArrayList<>();
        for (Node parentNode : parentNoodes) {
            int nodeNumber = parentNode.getLength() / parentNode.getNotWorking();
            int lastNodeLength = parentNode.getLength() % parentNode.getNotWorking();
            for (int i = 0; i < nodeNumber; i++) {
                childNodeList.add(new Node(parentNode.getNotWorking()));
            }
            if (lastNodeLength != 0) {
                childNodeList.add(new Node(lastNodeLength));
            }
        }
        return childNodeList;
    }
    private static String generateInput(List<Node> list) throws Exception {
        StringBuilder input = new StringBuilder();
        char binary = '0';
        for (Node node : list) {
            for(int i = 0; i < node.getLength(); i++) {
                input.append(binary);
            }
            binary = switchChar(binary);
        }
        return input.toString();
    }

    private static char switchChar(char ch) throws Exception {
        if (ch == '0') {
            return '1';
        }
        else if (ch == '1') {
            return '0';
        }
        else {
            throw new Exception("Shit is not right!");
        }
    }


    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int b = in.nextInt();
            int f = in.nextInt();
            List<Node> list = new ArrayList<>();
            Node node = new Node(n);
            node.setNotWorking(b);
            list.add(node);
            while (true) {
                list = generateChildNodes(list);
                String input = generateInput(list);
                list.isEmpty();
                break;
            }

        }
    }
}

class Node {
    private final Integer length;

    private Integer notWorking;

    public Node(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public int getNotWorking() {
        return notWorking;
    }

    public void setNotWorking(int notWorking) {
        this.notWorking = notWorking;
    }
}

