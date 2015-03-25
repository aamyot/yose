utils = {
    clickOnCell: function(cell) {
        var click = document.createEvent('Event');
        click.initEvent('click', true, true);
        cell.dispatchEvent(click);
    },

    cell: function cell(row, col) {
        return document.querySelector("#cell-"+ row + "x" + col);
    }
};