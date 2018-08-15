var user = require("services/user.js");

//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    user.checkLogin().then(() => {
      console.log('check login success');
      this.globalData.userInfo = wx.getStorageSync('userInfo');
      this.globalData.token = wx.getStorageSync('token');
      console.log(this.globalData.userInfo);
      console.log(this.globalData.token);
    }).catch(() => {
      // 登录
      wx.login({
        success: res => {
          // 发送 res.openId 到后台换取 openId, sessionKey, unionId
          new Promise(function (resolve, reject) {
            wx.request({
              url: 'http://localhost:8080/wx/login', //仅为示例，并非真实的接口地址
              method: 'POST',
              data: {
                code: res.code
              },
              header: {
                'content-type': 'application/json' // 默认值
              },
              success: function (res) {
                console.log(res.data);
                wx.setStorageSync('userInfo', {});
                wx.setStorageSync('token', res.data);
                resolve(res)
              },
              fail: function (err) {
                reject(err)
              }
            })
          })
        }
      })
    });


    // 获取用户信息
    // wx.getSetting({
    //   success: res => {
    //     if (res.authSetting['scope.userInfo']) {
    //       // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
    //       wx.getUserInfo({
    //         success: res => {
    //           // 可以将 res 发送给后台解码出 unionId
    //           this.globalData.userInfo = res.userInfo

    //           // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
    //           // 所以此处加入 callback 以防止这种情况
    //           if (this.userInfoReadyCallback) {
    //             this.userInfoReadyCallback(res)
    //           }
    //         }
    //       })
    //     }
    //   }
    // })
  },
  globalData: {
    userInfo: {},
    token: ''
  }
})