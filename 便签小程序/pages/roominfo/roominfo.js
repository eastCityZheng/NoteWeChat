// pages/add/add.js
var app=getApp();
var GetOneTeamPanel_url = app.appServlet.servlet + "GetOneTeamPanelServlet";
var GetOneTeamnote_url = app.appServlet.servlet + "GetOneTeamnoteServlet";
var GetOneTnJr_url = app.appServlet.servlet + "GetOneTnJrServlet";
var UpdateOneAttribute_url = app.appServlet.servlet + "UpdateOneAttributeServlet";
var GetTeampanelQRcode_url = app.appServlet.servlet + "GetTeampanelQRcodeServlet";
var updateTNName_url = app.appServlet.servlet + "updateTNNameServlet";

Page({

  /**
   * 页面的初始数据
   */
  // #19dfb8
  data: {
    username: "冬城君",
    roomname:"工作面板1",
    remarks:"这里是一条备注",
    colortype: 3,//颜色选择
    Mylongitude: 0,     //精度 
    Mylatitude: 0,     //纬度
    adr: '',           //地址
    name: '',           //地址名
    userN: '',     //input_name
    infoMess: '',
    tp_id:'',
    tp:null,
    tn:null,
    jr:null
  },
  //选择颜色
  s1: function (e) {
    
    this.setData({
      colortype: 0
    })
    wx.request({
      url: UpdateOneAttribute_url,
      data: {
        tablename: e.currentTarget.dataset.tablename,
        attr: e.currentTarget.dataset.attr,
        val: 0,
        tp_id:this.data.tp_id
      },
      header: {
        "Content-Type": "json"
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data)
      },
      fail: function (res) { },
      complete: function (res) { },
    })
  },
  s2: function (e) {
    this.setData({
      colortype: 1
    })
    wx.request({
      url: UpdateOneAttribute_url,
      data: {
        tablename: e.currentTarget.dataset.tablename,
        attr: e.currentTarget.dataset.attr,
        val: 1,
        tp_id: this.data.tp_id
      },
      header: {
        "Content-Type": "json"
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data)
      },
      fail: function (res) { },
      complete: function (res) { },
    })
  },
  s3: function (e) {
    this.setData({
      colortype: 2
    })
    wx.request({
      url: UpdateOneAttribute_url,
      data: {
        tablename: e.currentTarget.dataset.tablename,
        attr: e.currentTarget.dataset.attr,
        val: 2,
        tp_id: this.data.tp_id
      },
      header: {
        "Content-Type": "json"
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data)
      },
      fail: function (res) { },
      complete: function (res) { },
    })
  },
  s4: function (e) {
    this.setData({
      colortype: 3
    })
    wx.request({
      url: UpdateOneAttribute_url,
      data: {
        tablename: e.currentTarget.dataset.tablename,
        attr: e.currentTarget.dataset.attr,
        val: 3,
        tp_id: this.data.tp_id
      },
      header: {
        "Content-Type": "json"
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data)
      },
      fail: function (res) { },
      complete: function (res) { },
    })
  },
  s5: function (e) {
    this.setData({
      colortype: 4
    })
    wx.request({
      url: UpdateOneAttribute_url,
      data: {
        tablename: e.currentTarget.dataset.tablename,
        attr: e.currentTarget.dataset.attr,
        val: 4,
        tp_id: this.data.tp_id
      },
      header: {
        "Content-Type": "json"
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data)
      },
      fail: function (res) { },
      complete: function (res) { },
    })
  },
  s6: function (e) {
    this.setData({
      colortype: 5
    })
    wx.request({
      url: UpdateOneAttribute_url,
      data: {
        tablename: e.currentTarget.dataset.tablename,
        attr: e.currentTarget.dataset.attr,
        val: 5,
        tp_id: this.data.tp_id
      },
      header: {
        "Content-Type": "json"
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data)
      },
      fail: function (res) { },
      complete: function (res) { },
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
        wx.request({
          url: UpdateOneAttribute_url,
          data: {
            tablename: "Teampanel",
            attr: "tp_addr",
            val: res.name,
            tp_id: that.data.tp_id
          },
          header: {
            "Content-Type": "json"
          },
          method: 'GET',
          success: function (res) {
            console.log(res.data)
          },
          fail: function (res) { },
          complete: function (res) { },
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

    that.setData({
      tp_id:options.tp_id
    })

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
  changename:function(e){
    var that=this;
    wx.request({
      url: UpdateOneAttribute_url,
      data: {
        tablename:e.currentTarget.dataset.tablename,
        attr: e.currentTarget.dataset.attr,
        val:e.detail.value,
        tp_id: this.data.tp_id
      },
      header: {
        "Content-Type":"json"
      },
      method: 'GET',
      success: function(res) {
        console.log(res.data)
      },
      fail: function(res) {},
      complete: function(res) {},
    })
    wx.request({
      url: updateTNName_url,
      data: {
        u_id:app.appuserinfo.u_id,
        tp_id:that.data.tp_id,
        nickname: e.detail.value
      },
      header: {
        "Content-Type":"json"
      },
      method: 'GET',
      dataType: 'json',
      responseType: 'text',
      success: function(res) {
        console.log(res.data)
      },
      fail: function(res) {},
      complete: function(res) {},
    })
    
    
  },
  changeSwitch4:function(e){
    console.log(e)
    var val=e.detail.value=='true'?1:0
    wx.request({
      url: UpdateOneAttribute_url,
      data: {
        tablename: "jobrelation",
        attr: "jr_top",
        val: val,
        tp_id: this.data.tp_id
      },
      header: {
        "Content-Type": "json"
      },
      method: 'GET',
      success: function (res) {
        console.log(res.data)
      },
      fail: function (res) { },
      complete: function (res) { },
    })
  },
  //按钮点击
  addworkban: function () {
    if (this.data.userN.length == 0) {
      // this.setData({
      //   infoMess: '白板名称不能为空',
      // })
      wx.showModal({
        title: '',
        content: '白板名称不能为空',
        showCancel: false,
        confirmText: '确定',
        confirmColor: "#77a9fb"
      })
    }
  },

  QRcode: function (res) {
    var that=this;
    wx.request({
      url: GetTeampanelQRcode_url,
      data: {
        tp_id:that.data.tp_id,
        n_page:'roominfo'
      },
      header: {
        "Content-Type":"json"
      },
      method: 'GET',
      success: function(res) {
        // console.log(res.data)
        wx.navigateTo({
          url: '/pages/twowm/twowm?two='+res.data,
        })
      },
      fail: function(res) {},
      complete: function(res) {},
    })
    
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
    var that=this;
      wx.request({
        url: GetOneTeamPanel_url,
        data: {
          tp_id:that.data.tp_id
        },
        header: {
          "Content-Type":"json"
        },
        method: 'GET',
        success: function(res) {
          console.log(res.data)
          that.setData({
            tp:res.data['0'],
            colortype:res.data['0'].tp_color
          })
        },
        fail: function(res) {},
        complete: function(res) {},
      })
     
      wx.request({
        url: GetOneTeamnote_url,
        data: {
          u_id:app.appuserinfo.u_id,
          tp_id: this.data.tp_id
        },
        header: {
          "Content-Type": "json"
        },
        method: 'GET',
        success: function (res) {
          console.log(res.data)
          that.setData({
            tn: res.data['0']
          })
        },
        fail: function (res) { },
        complete: function (res) { },
      })

      wx.request({
        url: GetOneTnJr_url,
        data: {
          u_id: app.appuserinfo.u_id,
          tp_id: this.data.tp_id
        },
        header: {
          "Content-Type": "json"
        },
        method: 'GET',
        success: function(res) {
          console.log( res.data)
          that.setData({
            jr: res.data['0']
          })
        },
        fail: function(res) {},
        complete: function(res) {},
      })
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