(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[25],{"0IYL":function(e,a,t){"use strict";t.r(a);var l,n,r,o=t("XEnU"),u=(t("Telt"),t("Tckk")),c=t("XKWP"),g=t("N7Kx"),i=t("bvJU"),s=t("XPR9"),b=t("kFHX"),p=t("q1tI"),d=t.n(p),m=t("9kvl"),L=t("uJMD"),f=t("7pZ8"),h=t.n(f),v=t("i0ey"),k=t("KNI+"),D=t("2R2y"),j=t("bDL1"),I=(l=Object(m["a"])((function(e){var a=e.generalLog,t=e.global,l=e.loading;return{generalLog:a,global:t,loading:l.models.generalLog}})),l((r=function(e){function a(e){var t;return Object(c["a"])(this,a),t=Object(i["a"])(this,Object(s["a"])(a).call(this,e)),t.componentAuthority=h.a.generalLog.get,t.tabList=[{key:"basicInfo",show:t.checkAuthority(h.a.generalLog.get),tab:"\u57fa\u672c\u4fe1\u606f"}],t.getApiData=function(e){var a=e.generalLog.data;return a},t.checkNeedUpdate=function(e,a,l){return Object(D["a"])(t.state,e,a,l)},t.supplementLoadRequestParams=function(e){var a=e,l=t.state.generalLogId;return a.generalLogId=l,a},t.afterLoadSuccess=function(e,a,l,n){if(null!=(e||null)){var r=e||{message:""},o=r.message;t.setState({pageName:"".concat(j["fieldData"].message.label,"\uff1a\uff1a").concat(o)})}},t.pageHeaderLogo=function(){var e=t.state.metaData;return d.a.createElement(u["a"],{size:"large",src:null===e?"":e.imageUrl||"/noImageSmall.png"})},t.pageHeaderExtraContentData=function(){var e=t.state.metaData;return{textLabel:v["a"].statusNote.label,text:null===e?"":e.statusNote,timeLabel:v["a"].createTime.label,time:null===e?null:Object(L["X"])(e.createTime)}},t.pageHeaderContentData=function(){var e=t.state.metaData,a=[];return a.push({label:j["fieldData"].generalLogId.label,value:null===e?"":e.generalLogId||"",canCopy:!0}),a.push({label:v["a"].ip.label,value:null===e?"":e.ip||""}),a.push({label:v["a"].channelNote.label,value:null===e?"":e.channelNote||""}),a.push({label:v["a"].createTime.label,value:null===e?"":e.createTime||""}),a.push({label:v["a"].updateTime.label,value:null===e?"":e.createTime||""}),a},t.state=Object(o["a"])({},t.state,{},{pageName:"".concat(j["fieldData"].message.label,"\uff1a"),loadApiPath:"generalLog/get",backPath:"/generalLog/pageList/key",generalLogId:null}),t}return Object(b["a"])(a,e),Object(g["a"])(a,null,[{key:"getDerivedStateFromProps",value:function(e,a){return Object(L["r"])(e,a,{id:""},D["b"])}}]),a}(k["a"]),n=r))||n);a["default"]=I}}]);