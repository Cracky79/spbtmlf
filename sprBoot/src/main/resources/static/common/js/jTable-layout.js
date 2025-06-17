/**
 * JTable Layout 처리
 * 2021.11.11 최초 작성 
 * 
 * data-drag : true 인 경우 소팅이 무시된다 (data-sort)
 * 드래그 된 tr 영역은 마우스를 놓는 위치의 왼쪽으로 삽입 된다.
 * 
 * based on jquery 
 * @Cracky
 */

$(document).ready(function() {
    const heads = $("."+ _jTableClass + " th");
    const dataDrag = $("."+ _jTableClass).attr('data-drag');
    if ( dataDrag != undefined && dataDrag == 'true' ) {
	    $.each(heads, function(inx, row ) {
	        const head = $(row);
	        head.bind( "selectstart", function() { 
	        	return false ;
	        });
	        head.bind( "mousedown", mousedown);
	        head.bind( "mouseover", mouseover);
	        head.bind( "mouseout", mouseout);
	        head.bind( "mouseup", mouseup);
	
	        head.addClass("tableHead");
	    });
	    $(document.documentElement).bind( "mouseup", documentMouseup);
	    $(document.documentElement).bind( "mousemove", documentMouseMove);
    }
});

var documentMouseup = function (ev) {
    if (!dragTD) { return;}
    
    $(dragTD).removeClass("dragging");
    dragTD = null;    
    draggedDiv.remove();
    draggedDiv = null;
}

var documentMouseMove = function (ev){
    if (!draggedDiv) return;
    draggedDiv.css({top: ev.pageY + 5 + "px", left: ev.pageX + 10 + "px"});
}

let dragTD = null, draggedDiv=null;

let prvWidth = 0 , cWidth = 0;

var mousedown = function (ev){
    dragTD = this;
    prvWidth = $(this).css('width');
    $(this).addClass("dragging");
    draggedDiv = $("<div>");
    draggedDiv.addClass("draggedDiv");
    draggedDiv.css({top: ev.pageY + 5 + "px", left:ev.pageX + 10 + "px"});
    $(document.documentElement).append(draggedDiv);
    
    let dragTable = $("<table style='border:1px solid #d2d2d2;'>");
    draggedDiv.append(dragTable);
    
    const srcInx = dragTD.cellIndex;
    const rows = $("."+ _jTableClass + " tr"); 
    
    for (var x=0; x<rows.length; x++) {
        const tr = rows[x].cloneNode(false);
        dragTable.append(tr);
        const tds = rows[x].cells[srcInx].cloneNode(true);
        tr.appendChild(tds);
    }    
}

var mouseover = function (ev){
    if (dragTD === null) return;
    $(this).addClass("hovering");
}

var mouseout = function (ev){
    if (dragTD === null) return;
    $(this).removeClass("hovering");
}

function mouseup(ev){
    $(this).removeClass("hovering");
    $(dragTD).removeClass("dragging");
    draggedDiv.remove();
    draggedDiv = null;
    
    const srcInx = dragTD.cellIndex;
    const tarInx = this.cellIndex;
    const rows = $("."+ _jTableClass + " tr"); 
    
    for (let x=0; x<rows.length; x++) {
        tds = rows[x].cells;
        rows[x].insertBefore(tds[srcInx], tds[tarInx])
    }
    //$(dragTD).css("width",$(this).css('width'));
    //$(this).css("width",prvWidth);
    dragTD = null;
}

