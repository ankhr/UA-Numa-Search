package pageRank;

import java.util.*;

public class Annoy {

    private ArrayList<AnnoyNode> trees;

    public Annoy(ArrayList<PageNode> content, int numTrees) {
        for (int i = 0; i < numTrees; i++) {
            trees.add(new AnnoyNode(content, null));
        }
    }

    public ArrayList<PageNode> closestN(PageNode searchPoint,int n, int k) throws Exception {
        HashMap<PageNode,Double> distinctNeighbors = new HashMap<>();
        for (AnnoyNode tree: trees) {
            AnnoyNode current = tree;
            while(current.content.size()>k){
                if (current.l == null && current.r == null) {
                    break;
                } else if (searchPoint.vector[current.dimension] > current.splitValue) {
                    if (current.r != null) {
                        current = current.r;
                    } else {
                        current = current.l;
                    }
                } else {
                    if (current.l != null) {
                        current = current.l;
                    } else {
                        current = current.r;
                    }
                }
            }
            for (PageNode p :
                    current.content) {
                if (!distinctNeighbors.containsKey(p)) {
                    distinctNeighbors.put(p, sumOfSquaresDistance(p.vector, searchPoint.vector));
                }
            }
        }
        ArrayList<PageNode>
    }

    private Double sumOfSquaresDistance(double[] vector, double[] vector1) throws Exception {
        if (vector.length != vector1.length) {
            throw new Exception();
        }
        double sum = 0;
        for (int i = 0; i < vector.length; i++) {
            sum += Math.pow((vector[i] - vector1[i]), 2);
        }
        return sum;
    }

    private static class AnnoyNode {

        static Random random = new Random();
        // consider refactoring to linked list. Would be much
        // more memory efficient but slower to access
        // data and more awkward to get random elements
        ArrayList<PageNode> content;
        int dimension;
        double splitValue;
        AnnoyNode l;
        AnnoyNode r;
        AnnoyNode p;

        public AnnoyNode(ArrayList<PageNode> content, AnnoyNode parent) {
            this.content = content;
            this.p = parent;

            if (content.size() == 1) {
                return;
            }

            int id1 = random.nextInt(content.size());
            int id2 = random.nextInt(content.size());
            while (id1 == id2) {
                id2 = random.nextInt(content.size());
            }
            PageNode point1 = content.get(id1);
            PageNode point2 = content.get(id2);

            double maxGapSize = -1;
            for (int i = 0; i < point1.vector.length; i++) {
                double gapSize = Math.abs(point1.vector[i] - point2.vector[i]);
                if (gapSize > maxGapSize) {
                    this.dimension = i;
                    maxGapSize = gapSize;
                }
            }
            if (maxGapSize < 0) {
                return;
            }

            splitValue = (point1.vector[this.dimension] + point2.vector[this.dimension]) / 2;

            ArrayList<PageNode> leftContent = new ArrayList<>();
            ArrayList<PageNode> rightContent = new ArrayList<>();

            for (PageNode pageNode : content) {
                if (pageNode.vector[this.dimension] > this.splitValue) {
                    rightContent.add(pageNode);
                } else {
                    leftContent.add(pageNode);
                }
            }
            l = new AnnoyNode(leftContent, this);
            r = new AnnoyNode(rightContent, this);


        }

    }

    public static void main(String[] args) {
        //todo test this class
    }
}
