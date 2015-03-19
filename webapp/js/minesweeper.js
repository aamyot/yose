minesweeper = {

    load: function(grid, container) {

        for (var row = 0; row < grid.length; row++) {
            for (var col = 0; col < grid[row].length; col++) {
                var cell = container.querySelector("#cell-" + (row + 1) + "x" + (col + 1));
                cell.onclick = minesweeper.clickEventFactory(cell, grid[row][col]);
            }
        }
    },

    clickEventFactory: function(cell, state) {
        return function () {
            minesweeper.click(cell, state);
        };
    },

    click: function(cell, state) {
        if (state == 'bomb') {
            cell.className = 'lost';
        }
    }
}