(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[4],{b7Gq:function(a,e,t){"use strict";t.r(e);var l,n,c,s=t("XEnU"),u=(t("Telt"),t("Tckk")),r=t("XKWP"),o=t("N7Kx"),i=t("bvJU"),b=t("XPR9"),p=t("kFHX"),d=t("q1tI"),m=t.n(d),g=t("9kvl"),y=t("uJMD"),f=t("7pZ8"),h=t.n(f),v=t("i0ey"),W=t("KNI+"),k=t("AAa7"),D=t("CM8b"),j=(l=Object(g["a"])((function(a){var e=a.accessWay,t=a.global,l=a.loading;return{accessWay:e,global:t,loading:l.models.accessWay}})),l((c=function(a){function e(a){var t;return Object(r["a"])(this,e),t=Object(i["a"])(this,Object(b["a"])(e).call(this,a)),t.componentAuthority=h.a.accessWay.get,t.tabList=[{key:"basicInfo",show:t.checkAuthority(h.a.accessWay.get),tab:"\u57fa\u672c\u4fe1\u606f"}],t.getApiData=function(a){var e=a.accessWay.data;return e},t.checkNeedUpdate=function(a,e,l){return Object(k["a"])(t.state,a,e,l)},t.supplementLoadRequestParams=function(a){var e=a,l=t.state.accessWayId;return e.accessWayId=l,e},t.afterLoadSuccess=function(a,e,l,n){if(null!=(a||null)){var c=a||{name:""},s=c.name;t.setState({pageName:"".concat(D["fieldData"].name.label,"\uff1a").concat(s)})}},t.pageHeaderLogo=function(){var a=t.state.metaData;return m.a.createElement(u["a"],{size:"large",src:null===a?"":a.imageUrl||"/noImageSmall.png"})},t.pageHeaderExtraContentData=function(){var a=t.state.metaData;return{textLabel:v["a"].statusNote.label,text:null===a?"":a.statusNote,timeLabel:v["a"].createTime.label,time:null===a?null:Object(y["X"])(a.createTime)}},t.pageHeaderContentData=function(){var a=t.state.metaData,e=[];return e.push({label:D["fieldData"].accessWayId.label,value:null===a?"":a.accessWayId,canCopy:!0}),e.push({label:D["fieldData"].tag.label,value:null===a?"":a.tag,canCopy:!1}),e.push({label:v["a"].channelNote.label,value:null===a?"":a.channelNote}),e.push({label:v["a"].createTime.label,value:null===a?"":a.createTime}),e.push({label:v["a"].updateTime.label,value:null===a?"":a.createTime}),e},t.state=Object(s["a"])({},t.state,{},{pageName:"".concat(D["fieldData"].name.label,"\uff1a"),loadApiPath:"accessWay/get",backPath:"/accessWay/pageList/key",accessWayId:null}),t}return Object(p["a"])(e,a),Object(o["a"])(e,null,[{key:"getDerivedStateFromProps",value:function(a,e){return Object(y["r"])(a,e,{id:""},k["b"])}}]),e}(W["a"]),n=c))||n);e["default"]=j}}]);