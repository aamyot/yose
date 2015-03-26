utils = {
    clickOnCell: function(cell) {
        var click = document.createEvent('Event');
        click.initEvent('click', true, true);
        cell.dispatchEvent(click);
    },

    cell: function cell(row, col) {
        return document.querySelector("#cell-"+ row + "x" + col);
    },

    assertCellIsSafe: function(row, col, bombs) {
        var cell = this.cell(row, col);
        expect(cell.className).toEqual("safe");
        expect(cell.innerHTML).toEqual(bombs);
    }

};