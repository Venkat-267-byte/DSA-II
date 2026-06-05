class FenwickTree {

    int[] bit;
    int size;

    FenwickTree(int n) {

        size = n;

        bit = new int[n + 1];
    }

    void update(int index, int value) {

        while (index <= size) {

            bit[index] += value;

            index += index & (-index);
        }
    }

    int query(int index) {

        int sum = 0;

        while (index > 0) {

            sum += bit[index];

            index -= index & (-index);
        }

        return sum;
    }
}

class SegmentTree {

    int[] tree;
    int n;

    SegmentTree(int[] arr) {

        n = arr.length;

        tree = new int[4 * n];

        build(arr, 0, n - 1, 1);
    }

    void build(int[] arr,
               int start,
               int end,
               int node) {

        if (start == end) {

            tree[node] = arr[start];

            return;
        }

        int mid = (start + end) / 2;

        build(arr, start, mid, 2 * node);

        build(arr,
                mid + 1,
                end,
                2 * node + 1);

        tree[node] =
                tree[2 * node] +
                tree[2 * node + 1];
    }

    int rangeQuery(int start,
                   int end,
                   int node,
                   int left,
                   int right) {

        if (right < start || end < left)
            return 0;

        if (left <= start && end <= right)
            return tree[node];

        int mid = (start + end) / 2;

        return rangeQuery(start,
                mid,
                2 * node,
                left,
                right)

                +

                rangeQuery(mid + 1,
                        end,
                        2 * node + 1,
                        left,
                        right);
    }
}

public class casestudy2 {

    public static void main(String[] args) {

        int sales[] = {100, 200, 150, 300, 250};

        System.out.println(
                "===== Meesho Sales Analytics =====");

        FenwickTree ft =
                new FenwickTree(sales.length);

        for (int i = 0; i < sales.length; i++) {

            ft.update(i + 1, sales[i]);
        }

        int prefixSum = ft.query(3);

        System.out.println(
                "\nFenwick Tree Prefix Sum " +
                "(First 3 Days): " +
                prefixSum);

        SegmentTree st =
                new SegmentTree(sales);

        int rangeSum = st.rangeQuery(
                0,
                sales.length - 1,
                1,
                1,
                3);

        System.out.println(
                "\nSegment Tree Range Sum " +
                "(Day 2 to Day 4): " +
                rangeSum);
    }
}