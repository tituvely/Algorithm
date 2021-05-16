//* Time complexity
// Best: O(nlogn) Avg: O(nlogn) Worst: O(nlogn)
//* Space complexity
// O(n) 
//* 완전정렬(Stable sort) - 동일한 값에 대해 기존의 순서가 유지되는 정렬 방식

public class Merge {
    public static void sort(int[] data, int low, int high) {
        if (high - low < 2) { // 길이가 1이하일 경우
            return;
        }

        int mid = (low + high) / 2;

        sort(data, low, mid); 
        sort(data, mid, high);
        merge(data, low, mid, high);
    }

    public static void merge(int[] data, int low, int mid, int high) {
        int[] temp = new int[high-low];
        int t = 0, l = low, h = mid;

        while (l < mid && h < high) {
            if (data[l] < data[h]) {
                temp[t++] = data[l++];
            } else {
                temp[t++] = data[h++];
            }
        }

        while (l < mid) {
            temp[t++] = data[l++];
        }

        while (h < high) {
            temp[t++] = data[h++];
        }

        for (int i = low; i < high; i++) {
            data[i] = temp[i - low];
        }
    }

    public static void main(String[] args) {
        int[] data = { 2, 5, 1, 7, 9 };

        sort(data, 0, data.length);
        
        for (int i : data) {
            System.out.println(i);
        }
    }
}
