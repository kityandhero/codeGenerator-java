(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[22],{VZMd:function(e,a,t){"use strict";t.r(a);var n,r,l,i=t("k1fw"),o=(t("qVdP"),t("jsC+")),c=(t("lUTK"),t("BvKs")),m=(t("14J3"),t("BMrR")),d=(t("jCWc"),t("kPKH")),u=t("fWQN"),s=t("Nsem"),g=t("oZsa"),f=t("yKVA"),b=t("q1tI"),h=t.n(b),p=t("/MKj"),E=t("9kvl"),I=t("2PG1"),F=t("b6To"),O=t("uJMD"),j=t("ydnR"),w=t("7pZ8"),k=t.n(w),D=t("i0ey"),L=t("zmXK"),v=t("lLw4"),C=t("paK7"),y=t("4DO9"),K=(n=Object(p["c"])((function(e){var a=e.errorLog,t=e.global,n=e.loading;return{errorLog:a,global:t,loading:n.models.errorLog}})),n((l=function(e){function a(e){var t;return Object(u["a"])(this,a),t=Object(s["a"])(this,Object(g["a"])(a).call(this,e)),t.getApiData=function(e){var a=e.errorLog.data;return a},t.goToEdit=function(e){var a=e.errorLogId,t={pathname:"/errorLog/edit/load/".concat(a,"/key/basicInfo")};E["d"].push(t)},t.renderSimpleFormInitialValues=function(){var e={};return e[D["a"].channel.name]=j["unlimitedWithStringFlag"].flag,e},t.renderSimpleFormRow=function(){return h.a.createElement(h.a.Fragment,null,h.a.createElement(m["a"],{gutter:24},h.a.createElement(d["a"],{md:6,sm:24},t.renderSearchInputFormItem(y["fieldData"].message.label,y["fieldData"].message.name)),h.a.createElement(d["a"],{md:6,sm:24},t.renderSearchChannelFormItem(!0)),t.renderSimpleFormButton()))},t.getColumn=function(){return[{title:y["fieldData"].message.label,dataIndex:y["fieldData"].message.name,align:"left",render:function(e){return h.a.createElement(h.a.Fragment,null,h.a.createElement(v["a"],{tooltip:!0,lines:1},e))}},{title:y["fieldData"].errorLogId.label,dataIndex:y["fieldData"].errorLogId.name,width:120,align:"center",render:function(e){return h.a.createElement(h.a.Fragment,null,h.a.createElement(C["a"],{tooltip:!0,lines:1,removeChildren:!0,extraContent:h.a.createElement(h.a.Fragment,null,h.a.createElement("a",{onClick:function(){Object(O["h"])(e)}},Object(O["O"])(e,"***",2,6)))},e," [\u70b9\u51fb\u590d\u5236]"))}},{title:D["a"].channel.label,dataIndex:D["a"].channel.name,width:160,align:"center",render:function(e,a){return h.a.createElement(h.a.Fragment,null,h.a.createElement(v["a"],{tooltip:!0,lines:1},a.channelNote))}},{title:D["a"].createTime.label,dataIndex:D["a"].createTime.name,width:140,align:"center",sorter:!1,render:function(e){return h.a.createElement(h.a.Fragment,null,h.a.createElement(v["a"],{tooltip:!0,lines:1},""===(e||"")?"--":Object(O["q"])(Object(O["W"])(e),"YYYY-MM-DD HH:mm")))}},{title:D["a"].customOperate.label,dataIndex:D["a"].customOperate.name,width:120,fixed:"right",align:"center",render:function(e,a){return h.a.createElement(h.a.Fragment,null,h.a.createElement(o["a"].Button,{size:"small",onClick:function(){return t.goToEdit(a)},disabled:!t.checkAuthority(k.a.account.get),overlay:h.a.createElement(c["a"],{onClick:function(e){return t.handleMenuClick(e,a)}},h.a.createElement(c["a"].Item,{key:"analysis",disabled:!0},h.a.createElement(I["a"],null),"\u5206\u6790"))},h.a.createElement(F["a"],null),"\u67e5\u770b"))}}]},t.state=Object(i["a"])({},t.state,{},{pageName:"\u6a21\u5757\u5217\u8868",paramsKey:"53e093b4-70d0-4a37-8eee-e8bf2ff3f687",loadApiPath:"errorLog/pageList",dateRangeFieldName:"\u751f\u6210\u65f6\u6bb5"}),t}return Object(f["a"])(a,e),a}(L["a"]),r=l))||r);a["default"]=K}}]);