const menu_con = document.querySelector(".menu-con");
const function_menu = document.querySelector(".function-menu");
menu_con.addEventListener("click", () => {
    if(function_menu.classList.contains("menu")){
        function_menu.classList.remove("menu");
    }
    else{

        function_menu.classList.add("menu");
    }
  });

//   解决遮挡问题
const header = document.querySelector(".header");
const table_area = document.querySelector(".table-area");
// const function_menu = document.querySelector(".function-menu");

header.addEventListener("mouseover", () => {
    table_area.classList.add("table-area-index");
    function_menu.classList.add("table-area-index");
    
  });
  header.addEventListener("mouseout", () => {
    table_area.classList.remove("table-area-index");
    function_menu.classList.remove("table-area-index");
    
  });
// menu_con.addEventListener("mouseout", () => {
//     function_menu.classList.remove("menu");
    
// });
// 查询测试
// table_area.classList.add("table-area-h");
// const help = document.querySelector(".help");
// help.addEventListener("click", () => {
//     if(table_area.classList.contains("table-area-h")){
//         // function_menu.classList.remove("menu");
//         table_area.classList.remove("table-area-h");
//     }
//     else{

//         // function_menu.classList.add("menu");
//         table_area.classList.add("table-area-h");
//     }
//   });