package pageRank;

import java.util.ArrayList;
import java.util.Random;

public class Annoy {

    private ArrayList<AnnoyNode> trees;

    public Annoy(ArrayList<PageNode> content, int numTrees) {
        for (int i = 0; i < numTrees; i++) {
            trees.add(new AnnoyNode(content, null));
        }
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
            int id2  = random.nextInt(content.size());
            while (id1 == id2) {
                id2 = random.nextInt(content.size());
            }
            PageNode point1 = content.get(id1);
            PageNode point2 = content.get(id2);

            double maxGapSize = -1;
            for ( int i = 0; i<point1.vector.length;i++) {
                double gapSize = Math.abs(point1.vector[i] - point2.vector[i]);
                if (gapSize > maxGapSize) {
                    this.dimension = i;
                    maxGapSize = gapSize;
                }
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