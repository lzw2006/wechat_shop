const util = require("../utils/util.js");

function checkLogin() {
  return new Promise(function(resolve, reject){
    if(wx.getStorageSync('userInfo') && wx.getStorageInfoSync('token')) {
      util.checkSession().then(() => {
        resolve(true);
      }).catch(() => {
        reject(false)});
    }else {
      reject(false);
    }
  });
}

module.exports = {
  checkLogin
}