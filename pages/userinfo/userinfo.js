// pages/userinfo/userinfo.js
var app = getApp();
var openid_url = app.appServlet.servlet + "OpenIdServlet";
var register_url = app.appServlet.servlet + "RegisterServlet";
var datetime = require('../../util/getDate.js');


Page({

  /**
   * 页面的初始数据
   */
  data: {
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    time: datetime.formatTime(new Date()),
    movies: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.login({
      success: res => {
        if (res.code) {
          wx.request({
            //获取openid接口  
            url: openid_url,
            header: {
              "Content-Type": "json"
            },
            data: {
              js_code: res.code,
            },
            method: 'GET',
            success: function (res) {
              console.log(res.data)
              app.appuserinfo.openid = res.data.openid;
              console.log(app.appuserinfo.openid)
              // 查看是否授权
              wx.getSetting({
                success: function (res) {
                  if (res.authSetting['scope.userInfo']) {
                    wx.getUserInfo({
                      success: function (res) {
                        console.log(res.userInfo)
                        //帐号管理
                        wx.request({
                          url: register_url,
                          data: {
                            openid: app.appuserinfo.openid,
                            headimg: res.userInfo.avatarUrl,
                            nickname: res.userInfo.nickName
                          },
                          method: 'GET',
                          header: {
                            'Content-Type': 'json'
                          },
                          success: function (res) {
                            var data = res.data;
                            console.log(res.data);
                            if (res.data == 0) {
                              wx.showModal({
                                title: '提示',
                                content: '登录失败！',
                              })
                            } else {
                              app.appuserinfo.u_id = res.data['0'].u_id
                              app.appuserinfo.username=res.data['0'].nickname
                              app.appuserinfo.headimg=res.data['0'].headimg
                              //用户已经授权过
                              wx.switchTab({
                                url: '../index/index'
                              })
                            }

                            //  console.log(self.appuserinfo.aid);
                          },
                          fail: function (e) {
                            wx.showModal({
                              title: '提示',
                              content: '连接服务器失败，请稍后再试！',
                            })
                          },
                        })
                        
                      }
                    })
                  }
                }
              })

            }
          })
        } else { console.log('获取用户登录态失败！' + res.errMsg) }
      }
    })

  },
  bindGetUserInfo: function (e) {
    console.log(e.detail.userInfo)
    if (e.detail.userInfo) {
      //用户按了允许授权按钮
      //帐号管理
      wx.request({
        url: register_url,
        data: {
          openid: app.appuserinfo.openid,
          headimg: e.detail.userInfo.avatarUrl,
          nickname: e.detail.userInfo.nickName
        },
        method: 'GET',
        header: {
          'Content-Type': 'json'
        },
        success: function (res) {
          var data = res.data;
          console.log(res.data);
          if (res.data == 0) {
            wx.showModal({
              title: '提示',
              content: '登录失败！',
            })
          } else {
            app.appuserinfo.u_id = res.data['0'].u_id
            app.appuserinfo.username = res.data['0'].nickname
            app.appuserinfo.headimg = res.data['0'].headimg
            //用户已经授权过
            wx.switchTab({
              url: '../index/index'
            })
          }

        },
        fail: function (e) {
          wx.showModal({
            title: '提示',
            content: '连接服务器失败，请稍后再试！',
          })
        },
      })
    } else {
      //用户按了拒绝按钮
    }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})