package com.panagiotispetridis.day14;

import java.util.List;

public record Grid(List<List<Character>> data) {
    public void rotateClockwise() {
        int n = this.data().size();
        for (int first = 0; first < n / 2; first++) {
            var last = n - 1 - first;

            for (int i = first; i < last; i++) {
                int offset = i - first;
                char top = this.data().get(first).get(i);

                this.data().get(first).set(i, this.data().get(last - offset).get(first));
                this.data().get(last - offset).set(first, this.data().get(last).get(last - offset));
                this.data().get(last).set(last - offset, this.data().get(i).get(last));
                this.data().get(i).set(last, top);
            }
        }
    }

    public void rotateCounterClockwise() {
        int n = this.data().size();
        for (int first = 0; first < n / 2; first++) {
            int last = n - 1 - first;

            for (int i = first; i < last; i++) {
                int offset = i - first;
                char top = this.data().get(first).get(i);

                this.data().get(first).set(i, this.data().get(i).get(last));
                this.data().get(i).set(last, this.data().get(last).get(last - offset));
                this.data().get(last).set(last - offset, this.data().get(last - offset).get(first));
                this.data().get(last - offset).set(first, top);
            }
        }
    }

    public long getLoadTop() {
        this.rotateClockwise();
        var load = this.getLoadRight();
        this.rotateCounterClockwise();

        return load;
    }

    public long getLoadRight() {
        long load = 0;

        for (var row : this.data()) {
            for (int i = row.size()-1; i > -1; i--) {
                var c = row.get(i);
                if (c == 'O') {
                    load += i+1;
                }
            }
        }

        return load;
    }

    public void moveLoadTop() {
        // rotate clockwise to avoid jumping between rows to random points in memory
        this.rotateClockwise();

        moveLoadRight();

        // undo the previous rotation
        this.rotateCounterClockwise();
    }

    public void moveLoadRight() {
        for (var row : this.data()) {
            int curr = row.size()-1;
            for (int i = row.size()-1; i > -1; i--) {
                var c = row.get(i);
                switch (c) {
                    case 'O':
                        var tmp = row.get(i);
                        row.set(i, row.get(curr));
                        row.set(curr, tmp);
                        curr--;
                        break;
                    case '#':
                        curr = i-1;
                        break;
                    case '.':
                    default:
                }
            }
        }
    }

    public void print() {
        for (var row : this.data()) {
            for (var c : row) {
                System.out.printf("%s", c.toString());
            }
            System.out.println();
        }
    }
}
