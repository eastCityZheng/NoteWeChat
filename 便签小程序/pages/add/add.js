// pages/add/add.js
var app = getApp()

var InsertTeampanel_url = app.appServlet.servlet + "InsertTeampanelServlet";
var datetime = require('../../util/getDate.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    colortype: 0,//颜色选择
    Mylongitude: 0,     //精度 
    Mylatitude: 0,     //纬度
    adr: '',           //地址
    name: '',           //地址名
    userN:'',     //input_name
    infoMess:'',
    n_commont:''   //白板注释
  },
  //选择颜色
  s1: function (e) {
    this.setData({
      colortype: 0
    })
  },
  s2: function (e) {
    this.setData({
      colortype: 1
    })
  },
  s3: function (e) {
    this.setData({
      colortype: 2
    })
  },
  s4: function (e) {
    this.setData({
      colortype: 3
    })
  },
  s5: function (e) {
    this.setData({
      colortype: 4
    })
  },
  s6: function (e) {
    this.setData({
      colortype: 5
    })
  },
  //地图跳转
  changetoMap: function () {
    var that = this
    wx.chooseLocation({
      success: function (res) {
        // success
        console.log(res)
        that.setData({
          hasLocation: true,
          adr: res.address,
          name: res.name
        })
      },
      fail: function () {
        // fail
      },
      complete: function () {
        // complete
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    //获取经纬度
    wx.getLocation({
      success: function (res) {
        console.log(res)
        that.setData({
          /*hasLocation: true,*/
          Mylongitude: res.longitude,
          Mylatitude: res.latitude
        })
      },
    })
  },
  //input_name 取得
  userNameInput: function (e) {
    this.setData({
      userN: e.detail.value
    })
  },
  changecommont:function(e) {
    this.setData({
      n_commont: e.detail.value
    })
  },
  //按钮点击
  addworkban:function(){
    var that = this;  
    if (this.data.userN.length == 0) {
      // this.setData({
      //   infoMess: '白板名称不能为空',
      // })
      wx.showModal({
        title: '',
        content: '白板名称不能为空',
        showCancel:false,
        confirmText:'确定',
        confirmColor:"#77a9fb"
      })
  }else{
   
      wx.request({
        url: InsertTeampanel_url,
        data: {
          u_id: app.appuserinfo.u_id,
          tp_name: that.data.userN,
          tp_addr: that.data.name,
          tp_color: that.data.colortype,
          tp_comment: that.data.n_commont,
          tp_time: datetime.formatTime(new Date()),
          jr_nickname:app.appuserinfo.username
        },
        header: {
          "Content-Type": "json"
        },
        method: 'GET',
        success: function (res) {
          console.log(res.data)
          if(res.data==true)
          {
            wx.showToast({
              title: '创建成功！',
              icon: 'success',
              duration: 1000
            })
            setTimeout(function () {
              wx.navigateBack({
                url: '../gzs/gzs'
              })
            }, 1200)
          }else{
            wx.showToast({
              title: '创建失败！',
              icon: 'none',
              duration: 1000
            })
            setTimeout(function () {
              wx.navigateBack({
                url: '../gzs/gzs'
              })
            }, 1200)
          }
        },
        fail: function (res) { },
        complete: function (res) { },
      })
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