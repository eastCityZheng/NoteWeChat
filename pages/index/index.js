// pages/index/index.js
var app = getApp()

var GetOneUserNote_url = app.appServlet.servlet + "GetOneUserNoteServlet";
var SetTop_url = app.appServlet.servlet + "SetTopServlet";
var DeleteNote_url = app.appServlet.servlet + "DeleteNoteServlet";
var GetTeampanelQRcode_url = app.appServlet.servlet + "GetTeampanelQRcodeServlet";
var InsertPersonal_url = app.appServlet.servlet + "InsertPersonalServlet";
Page({

    /**
     * 页面的初始数据
     */
    data: {
        username: "",
        tatle: "photoshop,隐藏参考线快捷键(ctrl+;)无效?",
        time: "周五 04/20 17:49",
        xxsrc: ["../../images/xx1.png", "../../images/xx2.png"],
        colorx: ["#ff6666", "#498eff", "#c68efe", "#ffc949", "#feb0fe", "#ffa94d"],
        indexx: 0,
        delBtnWidth: 77,
        deui: 0,
        deu: null,
        ht: null,
        hh: 0,
        animationData: {},
        flag: true,
        src: '',
        program: null,
        QRcode: ''
    },
    //手指刚放到屏幕触发 
    touchS: function(e) {
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
    touchM: function(e) {
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
    },
    touchE: function(e) {
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
    tofriend: function(e) {
        wx.navigateTo({
            url: '../mbxz/mbxz',
        })
    },
    delItem: function(e) {
        var that = this;
        var index = e.target.dataset.index;
        console.log("删除" + index);

        wx.request({
            url: DeleteNote_url,
            data: {
                n_id: e.target.dataset.n_id
            },
            header: {
                "Content-Type": "json"
            },
            method: 'GET',
            success: function(res) {
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
            fail: function(res) {},
            complete: function(res) {},
        })
    },
    deu: function(res) {
        var that = this
        if (this.data.deui == 0) {
            that.setData({
                deu: 45,
                deui: 1,
                hh: 270,
            })
        } else {
            that.setData({
                deu: 0,
                deui: 0,
                hh: 0
            })
        }

    },
    sys: function(res) {
        wx.scanCode({
            success: (pres) => {
                if (pres.result == app.appuserinfo.u_id) {
                    wx.showToast({
                        title: '不能扫描自己的二维码！',
                        icon: 'none',
                        duration: 1000
                    })
                } else {
                    wx.request({
                        url: InsertPersonal_url,
                        data: {
                            uu_id: pres.result,
                            u_id: app.appuserinfo.u_id
                        },
                        header: {
                            "Content-Type": "json"
                        },
                        method: 'GET',
                        dataType: 'json',
                        responseType: 'text',
                        success: function(res) {
                            console.log(res.data)
                            if (res.data == true) {

                                wx.navigateTo({
                                    url: "../Other/Other?u_id=" + pres.result,
                                })
                            } else {
                                wx.showToast({
                                    title: '扫描失败！',
                                    icon: 'none',
                                    duration: 1000
                                })
                            }
                        },
                        fail: function(res) {

                        },
                        complete: function(res) {},
                    })
                }


            }
        })
    },
    swm: function(res) {
        var that = this;
        wx.navigateTo({
            url: '/pages/twowm/twowm?two=' + that.data.QRcode,
        })
    },
    //查看便签
    red: function(res) {
        wx.navigateTo({
            url: '/pages/bq/bq?n_id=' + res.currentTarget.dataset.n_id + '&&n_page=index',
        })
    },
    xx: function(res) {
        var that = this
        var n_top = parseInt(res.target.dataset.n_top)
        var index = parseInt(res.target.dataset.index)
        var cha = "program[" + index + "].n_top"
        if (n_top == 0) {
            that.setData({
                [cha]: 1
            })
            wx.request({
                url: SetTop_url,
                data: {
                    id: res.target.dataset.n_id,
                    n_top: 1,
                    n_type: "note"
                },
                header: {
                    "ContentType": "json"
                },
                method: 'GET',
                success: function(res) {
                    console.log("置顶" + res.data)
                    wx.request({
                        url: GetOneUserNote_url,
                        data: {
                            u_id: app.appuserinfo.u_id
                        },
                        header: {
                            "Content-Type": "json"
                        },
                        method: 'GET',
                        dataType: 'json',
                        success: function(res) {
                            console.log(res.data)
                            that.setData({
                                program: res.data
                            })
                        },
                        fail: function(res) {},
                        complete: function(res) {},
                    })
                },
                fail: function(res) {},
                complete: function(res) {},
            })
        } else {
            that.setData({
                [cha]: 0
            })
            wx.request({
                url: SetTop_url,
                data: {
                    id: res.target.dataset.n_id,
                    n_top: 0,
                    n_type: "note"
                },
                header: {
                    "ContentType": "json"
                },
                method: 'GET',
                success: function(res) {
                    console.log("置顶" + res.data)
                    wx.request({
                        url: GetOneUserNote_url,
                        data: {
                            u_id: app.appuserinfo.u_id
                        },
                        header: {
                            "Content-Type": "json"
                        },
                        method: 'GET',
                        dataType: 'json',
                        success: function(res) {
                            console.log(res.data)
                            that.setData({
                                program: res.data
                            })
                        },
                        fail: function(res) {},
                        complete: function(res) {},
                    })
                },
                fail: function(res) {},
                complete: function(res) {},
            })
        }
    },
    translation: function(e) {
        wx.navigateTo({
            url: '/pages/record/record?page=index',
        })
    },
    document: function(res) {
        wx.navigateTo({
            url: '/pages/BuildNote/BuildNote?page=index',
        })
    },
    //弹出层show
    video: function() {
        var that = this;
        this.setData({
            flag: false,
            textareashow: true,
            imgtop: 440,
        })
    },
    //hide
    hide: function() {
        this.setData({
            flag: true,
            textareashow: false,
            imgtop: 70,
        })
    },
    //跳转
    select: function() {
        var that = this
        wx.chooseVideo({
            sourceType: ['album'],
            success: function(res) {
                //  setTimeout(function(){
                wx.navigateTo({
                    url: '/pages/BuildNote2/BuildNote2?extra=' + JSON.stringify(res.tempFilePath) + "&&page=index",
                })
                //  },0)
            }
        })
    },
    pai: function() {
        var that = this
        wx.chooseVideo({
            sourceType: ['camera'],
            success: function(res) {
                //  setTimeout(function(){
                wx.navigateTo({
                    url: '/pages/BuildNote2/BuildNote2?extra=' + JSON.stringify(res.tempFilePath) + "&&page=index",
                })
                //  },0)
            }
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function(options) {
        this.setData({
            ht: wx.getSystemInfoSync().windowHeight-50
        })
        console.log(this.data.ht)
        var that = this;
        wx.request({
            url: GetTeampanelQRcode_url,
            data: {
                tp_id: app.appuserinfo.u_id,
                n_page: 'index'
            },
            header: {
                "Content-Type": "json"
            },
            method: 'GET',
            success: function(res) {
                that.setData({
                    QRcode: res.data
                })
            },
            fail: function(res) {},
            complete: function(res) {},
        })

    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function() {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function() {
        var that = this;
        wx.request({
            url: GetOneUserNote_url,
            data: {
                u_id: app.appuserinfo.u_id
            },
            header: {
                "Content-Type": "json"
            },
            method: 'GET',
            dataType: 'json',
            success: function(res) {
                console.log(res.data)
                that.setData({
                    program: res.data,
                    username: app.appuserinfo.username
                })
            },
            fail: function(res) {},
            complete: function(res) {},
        })
    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function() {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function() {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function() {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function() {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function() {
        return {
            title: '微享便签',
            desc: '将自己的公开便签分享给他人！',
            path: '/pages/Other/Other?u_id=' + app.appuserinfo.u_id + "&&nickname=" + app.appuserinfo.username
        }
    }
})