import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1248 {
    static BufferedReader br;
    static StringTokenizer stz;
    static int T, V, E, v1, v2;

    public static void main(String[] args) throws Exception {
        // System.setIn(new BufferedInputStream(new FileInputStream("./SW1248_input.txt")));
        // br = new BufferedReader(new InputStreamReader(System.in));
        
        // T = Integer.parseInt(br.readLine());
        // for(int i=1; i<=T; i++) {
        //     stz = new StringTokenizer(br.readLine());
        //     V = Integer.parseInt(stz.nextToken());
        //     E = Integer.parseInt(stz.nextToken());
        //     v1 = Integer.parseInt(stz.nextToken());
        //     v2 = Integer.parseInt(stz.nextToken());

        //     stz = new StringTokenizer(br.readLine());
        //     // Tree tree = new Tree();
        //     for(int j=0; j<E; j++) {
        //     }
        // }

        // 트리 생성
		Tree tree = new Tree();
		
		// 노드 생성
		Node node1 = tree.addNode(1);
		Node node2 = tree.addNode(2);
		Node node3 = tree.addNode(3);
		Node node4 = tree.addNode(4);
		Node node5 = tree.addNode(5);
		Node node6 = tree.addNode(6);
		Node node7 = tree.addNode(7);
		
		// 트리 연결관계 생성
		/*  트리 모양       
		 *        1
		 *     2     3
		 *   4  5  6   7
		 */
		node1.addLeft(node2);
		node1.addRight(node3);
		node2.addLeft(node4);
		node2.addRight(node5);
		node3.addLeft(node6);
		node3.addRight(node7);
		
		// 순회
		tree.preOrder(node1);
		System.out.println();
		tree.inOrder(node1);
		System.out.println();
		tree.postOrder(node1);
		System.out.println();
		
		// 삭제
		node2.deleteLeft();
		node3.deleteRight();
    }
}

class Tree {
	int count;
	
	public Tree() {
		count = 0;
	}
	
	public class Node {
		Object data;
		Node left;
		Node right;
	
		// 생성 시 매개변수를 받아 초기화하는 방법으로만 선언 가능
		public Node(Object data) {
			this.data = data;
			left = null;
			right = null;
		}

		public void addLeft(Node node) {
			left = node;
			count++;
		}

		public void addRight(Node node) {
			right = node;
			count++;
		}

		public void deleteLeft() {
			left = null;
			count--;
		}

		public void deleteRight() {
			right = null;
			count--;
		}
	}
	
	public Node addNode(Object data) {
		Node n = new Node(data);
		return n;
	}

}
