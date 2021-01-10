import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class H {

    private static final Set<Integer> tempSet = new HashSet<>();
    private static List<Set<Integer>> graph;
    private static int[] colors;
    private static boolean[] used;
    private static Edge globalEdge;
    private static boolean flag;
    private static int n;
    private static int m;

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);


        n = in.nextInt();
        m = in.nextInt();

        colors = new int[n];
        used = new boolean[n];

        graph = Stream.generate(() -> new HashSet<Integer>())
                .limit(n)
                .collect(Collectors.toList());

        for (int i = 0; i < m; i++) {
            int x = in.nextInt() - 1;
            int y = in.nextInt() - 1;
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        List<GeneratedGraph> g = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                tempSet.clear();
                mainDfs(i);
                List<HashSet<Integer>> sets = Stream.generate(() -> new HashSet<Integer>())
                        .limit(n)
                        .collect(Collectors.toList());
                int counter = 0;
                for (Integer setElement : tempSet) {
                    for (Integer neighbor : graph.get(setElement)) {
                        if (tempSet.contains(neighbor)) {
                            sets.get(setElement).add(neighbor);
                            counter++;
                        }
                    }
                }
                g.add(new GeneratedGraph(sets, tempSet.size(), counter / 2));
            }
        }

        List<Integer> ans = oneElementList(1);

        for (GeneratedGraph generatedGraph : g) {
            List<Integer> temp = getAns(generatedGraph);
            ans = polynomials(ans, temp);
        }

        int i = 0;
        Collections.reverse(ans);

        while (i < ans.size() && ans.get(i) == 0) {
            i++;
        }

        out.println(ans.size() - i - 1);
        for (int j = i; j < ans.size(); j++) {
            out.print(ans.get(j) + " ");
        }
        out.close();
    }

    private static void dropAll() {
        globalEdge = new Edge(-1, -1);
        flag = false;
        colors = new int[n];
    }

    private static void dfs(GeneratedGraph g, int x, int p) {
        if (flag) {
            return;
        }
        while (g.dead.contains(x)) {
            x++;
        }
        colors[x] = 1;
        for (Integer element : g.graph.get(x)) {
            if (colors[element] == 0) {
                dfs(g, element, x);
            } else if (colors[element] == 1 && p != element) {
                globalEdge = new Edge(x, element);
                flag = true;
            }
        }
        colors[x] = 2;
    }

    private static List<Integer> getAns(GeneratedGraph g) {
        if (g.n == g.m + 1) {
            return calcTree(g);
        }

        dropAll();
        dfs(g, 0, -1);
        Edge edge = globalEdge;
        System.out.println(edge.x + " " + edge.y);
        List<Integer> first = getAns(subEdge(new GeneratedGraph(g), edge));
        List<Integer> second = getAns(contrEdge(new GeneratedGraph(g), edge));
        return calc(first, second);
    }

    private static GeneratedGraph subEdge(GeneratedGraph g, Edge edge) {
        GeneratedGraph g1 = new GeneratedGraph(g);
        g1.graph.get(edge.x).remove(edge.y);
        g1.graph.get(edge.y).remove(edge.x);
        g1.m--;
        return g1;
    }

    private static GeneratedGraph contrEdge(GeneratedGraph g, Edge edge) {
        GeneratedGraph g1 = new GeneratedGraph(g);
        for (Integer to : g1.graph.get(edge.x)) {
            g1.graph.get(edge.y).add(to);
            g1.graph.get(to).add(edge.y);
        }

        for (Set<Integer> set : g1.graph) {
            set.remove(edge.x);
        }

        g1.graph.get(edge.y).remove(edge.y);
        g1.dead.add(edge.x);
        g1.n--;
        int m1 = 0;
        for (int i = 0; i < g1.graph.size(); i++) {
            if (!g1.dead.contains(i)) {
                m1 += g1.graph.get(i).size();
            }
        }
        g1.m = m1 / 2;
        return g1;
    }

    private static List<Integer> calc(List<Integer> a, List<Integer> b) {
        List<Integer> ans = Stream.generate(() -> 0)
                .limit(Math.max(a.size(), b.size()))
                .collect(Collectors.toList());

        for (int i = 0; i < ans.size(); i++) {
            if (i < a.size()) {
                ans.set(i, ans.get(i) + a.get(i));
            }
            if (i < b.size()) {
                ans.set(i, ans.get(i) - b.get(i));
            }
        }

        return ans;
    }

    private static List<Integer> calcTree(GeneratedGraph g) {
        List<Integer> powers = oneElementList(1);
        for (int i = 0; i < g.n - 1; i++) {
            powers = power(powers);
        }
        List<Integer> ans = oneElementList(0);
        ans.addAll(powers);
        return ans;
    }

    private static List<Integer> oneElementList(final int x) {
        List<Integer> res = new ArrayList<>();
        res.add(x);
        return res;
    }

    private static List<Integer> power(List<Integer> lst) {
        List<Integer> ans = Stream.generate(() -> 0)
                .limit(lst.size() + 1)
                .collect(Collectors.toList());

        for (int i = 0; i < lst.size(); i++) {
            ans.set(i, ans.get(i) - lst.get(i));
            ans.set(i + 1, ans.get(i + 1) + lst.get(i));
        }
        return ans;
    }

    private static List<Integer> polynomials(List<Integer> a, List<Integer> b) {

        List<Integer> a1 = a;
        List<Integer> b1 = b;

        if (a.size() < b.size()) {
            a1 = b;
            b1 = a;
        }

        LinkedList<Integer> ans = new LinkedList<>();

        for (int i = 0; i < 2L * a1.size() + 1; i++) {
            ans.add(0);
        }

        for (int i = 0; i < a1.size(); i++) {
            for (int j = 0; j < b1.size(); j++) {
                ans.set(i + j, ans.get(i + j) + a.get(i) * b.get(j));
            }
        }

        while (!ans.isEmpty() && ans.get(ans.size() - 1) == 0) {
            ans.pop();
        }
        return ans;
    }

    private static void mainDfs(final int v) {
        used[v] = true;
        tempSet.add(v);
        for (Integer to : graph.get(v)) {
            if (!used[to]) {
                mainDfs(to);
            }
        }
    }

    private static class GeneratedGraph {
        List<HashSet<Integer>> graph;
        Set<Integer> dead = new HashSet<>();
        int n;
        int m;

        public GeneratedGraph(final List<HashSet<Integer>> graph, final int n, final int m) {
            this.graph = graph;
            this.n = n;
            this.m = m;
        }

        public GeneratedGraph(GeneratedGraph g) {
            this.graph = new ArrayList<>();
            for (HashSet<Integer> set : g.graph) {
                this.graph.add((HashSet<Integer>) set.clone());
            }
            this.n = g.n;
            this.m = g.m;
            this.dead = new HashSet<>(g.dead);
        }
    }

    public static Edge minusEdge = new Edge(-1, -1);
    private static class Edge {
        int x;
        int y;


        public Edge(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }

    private static class IntList {

        private int[] array;
        private int size;

        public IntList() {
            init();
        }

        private void init() {
            array = new int[8];
            size = 0;
        }

        public void add(int value) {
            addToList(value);
        }

        private void addToList(int value) {
            if (size == array.length) {
                array = Arrays.copyOf(array, array.length + (array.length >> 1));
            }
            array[size++] = value;
        }

        public int size() {
            return this.size;
        }

        public int get(int ind) {
            return array[ind];
        }

    }
}
