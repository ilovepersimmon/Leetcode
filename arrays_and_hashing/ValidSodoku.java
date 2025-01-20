class Solution {
    // Determine if a 9 x 9 Sudoku board is valid. Only the filled 
    // cells need to be validated according to the following rules:
    // each cell: . or 1 - 9
    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        HashSet<Character>[] rowSets = new HashSet[N];
        HashSet<Character>[] colSets = new HashSet[N];
        HashSet<Character>[] boxSets = new HashSet[N];

        for (int i = 0; i < N; i++) {
            rowSets[i] = new HashSet<>();
            colSets[i] = new HashSet<>();
            boxSets[i] = new HashSet<>();
        }
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char cellChar = board[i][j];

                if (cellChar == '.') {
                    continue;
                }

                if (rowSets[i].contains(cellChar)) {
                    return false;
                }
                rowSets[i].add(cellChar);

                if (colSets[j].contains(cellChar)) {
                    return false;
                }
                colSets[j].add(cellChar);

                int box = (i / 3) * 3 + j / 3;
                if (boxSets[box].contains(cellChar)) {
                    return false;
                }
                boxSets[box].add(cellChar);
            }
        }
        return true;
    }
}
