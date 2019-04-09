// pages/gzs/gzs.js
var app=getApp();
var GetOneUserTeampanel_url = app.appServlet.servlet + "GetOneUserTeampanelServlet";
var SetTop_url = app.appServlet.servlet + "SetTopServlet";
var DeleteTeampanel_url = app.appServlet.servlet + "DeleteTeampanelServlet";


Page({

    /**
     * 页面的初始数据
     */
    data: {
        ht: null,
        username:"",
        time: "周五 04/20 17:49",
        tp_top: 0,
        delBtnWidth: 77,
        deui: 0,
        deu: null,
        hh: 0,
        animationData: {},
        star: ['../../images/xxx1.png','../../images/xxx2.png'],
        cimg: ["../../images/r4.png", "../../images/blue.png", "../../images/p3.png","../../images/green2.png", ,"../../images/r4.png"],
        program: null
    },
    //手指刚放到屏幕触发 
    touchS: function (e) {
        console.log("touchS" + e);
        //判断是否只有一个触摸点 
        if (e.touches.length == 1) {
            this.setData({
                //记录触摸起始位置的X坐标 
                startX: e.touches[0].clientX
            });
        }
    },
    //触摸时触发，手指在屏幕上每移动一次，触发一次 
    touchM: function (e) {
        console.log("touchM:" + e);
        var that = this
        if (e.touches.length == 1) { //记录触摸点位置的X坐标 
            var moveX = e.touches[0].clientX;
            //计算手指起始点的X坐标与当前触摸点的X坐标的差值 
            var disX = that.data.startX - moveX;
            //delBtnWidth 为右侧按钮区域的宽度 
            var delBtnWidth = that.data.delBtnWidth;
            var txtStyle = "";
            if (disX == 0 || disX < 0) {
                //如果移动距离小于等于0，文本层位置不变 
                txtStyle = "left:0px";
            } else if (disX > 0) {
                //移动距离大于0，文本层left值等于手指移动距离
                txtStyle = "left:-" + disX + "px";
                if (disX >= delBtnWidth) {
                    //控制手指移动距离最大值为删除按钮的宽度 
                    txtStyle = "left:-" + delBtnWidth + "px";
                }
            }
            //获取手指触摸的是哪一个item 
            var index = e.currentTarget.dataset.index;
            var list = that.data.program;
            //将拼接好的样式设置到当前item中 
            // list[index].txtStyle = txtStyle;
            var asd = "program[" + index + "].txtStyle"
            //更新列表的状态 
            this.setData({
                [asd]: txtStyle
            });
        }
    }, touchE: function (e) {
        console.log("touchE" + e);
        var that = this
        if (e.changedTouches.length == 1) {
            //手指移动结束后触摸点位置的X坐标 
            var endX = e.changedTouches[0].clientX;
            //触摸开始与结束，手指移动的距离 
            var disX = that.data.startX - endX;
            var delBtnWidth = that.data.delBtnWidth;
            //如果距离小于删除按钮的1/2，不显示删除按钮 
            var txtStyle = disX > delBtnWidth / 2 ? "left:-" + delBtnWidth + "px" : "left:0px";
            //获取手指触摸的是哪一项 
            var index = e.currentTarget.dataset.index;
            var list = that.data.program;
            // list[index].txtStyle = txtStyle;
            var asd = "program[" + index + "].txtStyle"
            //更新列表的状态 
            this.setData({
                [asd]: txtStyle
            });
        }
    },
    delItem: function (e) {
        var index = e.target.dataset.index;
        console.log("删除" + index);
        var that=this;

        wx.request({
          url: DeleteTeampanel_url,
          data: {
            tp_id: e.target.dataset.tp_id
          },
          header: {
            "Content-Type": "json"
          },
          method: 'GET',
          success: function (res) {
            console.log(res.data)
            if (res.data == true) {
              wx.showToast({
                title: '删除成功！',
                icon: 'success',
                duration: 1000
              })
              var program = that.data.program;
              var newprogram = that.data.program;
              newprogram.splice(index, 1)
              that.setData({
                program: newprogram
              });
            } else {
              wx.showToast({
                title: '删除失败！',
                icon: 'none',
                duration: 1000
              })
            }
          },
          fail: function (res) { },
          complete: function (res) { },
        })
    },
    deu: function (res) {
      wx.navigateTo({
        url: '/pages/add/add',
      })
    },
    sys: function (res) {
        wx.scanCode({
            success: (res) => {
                console.log(res)
                wx.request({
                  url: app.appServlet.servlet+res.result,
                  data: {
                    u_id:app.appuserinfo.u_id,
                    jr_nickname:app.appuserinfo.username
                  },
                  header: {
                    "Content-Type":"json"
                  },
                  method: 'GET',
                  success: function(res) {
                    console.log(res.data)
                    if(res.data=="again"){
                      wx.showToast({
                        title: '您已在此工作室中！',
                        icon: 'none',
                        duration: 1000
                      })
                    }else if(res.data==true){
                      wx.showToast({
                        title: '添加成功！',
                        icon: 'success',
                        duration: 1000
                      })
                    }else{
                      wx.showToast({
                        title: '添加失败！',
                        icon: 'none',
                        duration: 1000
                      })
                    }

                    
                  },
                  fail: function(res) {},
                  complete: function(res) {},
                })
            }
        })
    },
    swm: function (res) {
        wx.navigateTo({
            url: '/pages/twowm/twowm',
        })
    },
    xx: function (res) {

        var that = this
        var tp_top = parseInt(res.target.dataset.tp_top)
        var index = parseInt(res.target.dataset.index)
        var cha = "program[" + index + "].tp_top"
        if (tp_top == 0) {
            that.setData({
                [cha]: 1
            })
            wx.request({
              url: SetTop_url,
              data: {
                id: res.target.dataset.tp_id,
                u_id:app.appuserinfo.u_id,
                n_top: 1,
                n_type: "teampanle"
              },
              header: {
                "ContentType": "json"
              },
              method: 'GET',
              success: function (res) {
                console.log("置顶" + res.data)
                wx.request({
                  url: GetOneUserTeampanel_url,
                  data: {
                    u_id: app.appuserinfo.u_id
                  },
                  header: {
                    "Content-Type": "json"
                  },
                  method: 'GET',
                  success: function (res) {
                    console.log(res.data)
                    that.setData({
                      program: res.data
                    })
                  },
                  fail: function (res) { },
                  complete: function (res) { },
                })
        }})
        }
        else {
            that.setData({
                [cha]: 0
            })
            wx.request({
              url: SetTop_url,
              data: {
                id: res.target.dataset.tp_id,
                u_id: app.appuserinfo.u_id,
                n_top: 0,
                n_type: "teampanle"
              },
              header: {
                "ContentType": "json"
              },
              method: 'GET',
              success: function (res) {
                console.log("置顶" + res.data)
                wx.request({
                  url: GetOneUserTeampanel_url,
                  data: {
                    u_id: app.appuserinfo.u_id
                  },
                  header: {
                    "Content-Type": "json"
                  },
                  method: 'GET',
                  success: function (res) {
                    console.log(res.data)
                    that.setData({
                      program: res.data
                    })
                  },
                  fail: function (res) { },
                  complete: function (res) { },
                })
              },
              fail: function (res) { },
              complete: function (res) { },
            })
        }
    },

    translation: function (e) {
        wx.navigateTo({
            url: '/pages/record/record',
        })
    },

    v2_1:function(e){
        wx.navigateTo({
          url: '/pages/gzmb/gzmb?tp_id=' + e.currentTarget.dataset.tp_id + "&&tp_name=" + e.currentTarget.dataset.tp_name,
        })
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.setData({
            ht: wx.getSystemInfoSync().windowHeight-50
        })
        console.log(this.data.ht)
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
      var that = this;
      wx.request({
        url: GetOneUserTeampanel_url,
        data: {
          u_id: app.appuserinfo.u_id
        },
        header: {
          "Content-Type": "json"
        },
        method: 'GET',
        success: function (res) {
          console.log(res.data)
          that.setData({
            program: res.data,
            username:app.appuserinfo.username
          })
        },
        fail: function (res) { },
        complete: function (res) { },
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