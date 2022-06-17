const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");
/* 以下调试模块，开启后页面初始进入注册模块 */
// container.classList.add("sign-up-mode");
sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});

// 需要的正则匹配表达式
const RegList = [
  /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/, 						// 账号匹配
  /^[a-zA-Z]\w{5,17}$/, 									// 密码匹配
  /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, 		// 邮箱匹配
];

const username = document.getElementsByName("username");
const password = document.getElementsByName("password");
const email = document.getElementsByName("email");
const tipsFalse = document.getElementsByClassName("tips-false");
const icon = document.getElementsByClassName("icon-tips");
username[0].addEventListener("change", function (){checkUsername(0)});  // 登录表单用户名输入控件
username[1].addEventListener("change", function (){checkUsername(1)});  // 注册表单用户名输入控件
password[0].addEventListener("change", function (){checkPassword(0)});  // 登录表单密码输入控件
password[1].addEventListener("change", function (){checkPassword(1)});  // 注册表单密码输入控件
email[0].addEventListener("change", checkEmail);					  	// 注册表单邮箱输入控件
// 检测用户名输入是否符合规范
function checkUsername(n) {
  var m = 2*n; 	// 0-0；1-2
  if (RegList[0].test(username[n].value)) {
    icon[m].setAttribute("src", "img/true.svg");
    tipsFalse[m].style.display = "none";
  } else {
    icon[m].setAttribute("src", "img/false.svg");
    tipsFalse[m].style.display = "block";
  }
  icon[m].style.display = "block";
}
// 检测密码输入是否符合规范
function checkPassword(n) {
  var m = n*3+1;	// 0-1；1-4
  if (RegList[1].test(password[n].value)) {
    icon[m].setAttribute("src", "img/true.svg");
    tipsFalse[m].style.display = "none";
  } else {
    icon[m].setAttribute("src", "img/false.svg");
    tipsFalse[m].style.display = "block";
  }
  icon[m].style.display = "block";
}
// 检测邮箱输入是否符合规范
function checkEmail(){
  var m = 3;
  if (RegList[2].test(email[0].value)) {
    icon[m].setAttribute("src", "img/true.svg");
    tipsFalse[m].style.display = "none";
  } else {
    icon[m].setAttribute("src", "img/false.svg");
    tipsFalse[m].style.display = "block";
  }
  icon[m].style.display = "block";
}