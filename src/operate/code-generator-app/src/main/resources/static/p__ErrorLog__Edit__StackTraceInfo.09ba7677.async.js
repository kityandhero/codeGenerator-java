(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[23],{PoZt:function(e,t,a){"use strict";a.r(t);var n,r,o,c=a("k1fw"),l=(a("IzEo"),a("bx4M")),i=(a("T2oS"),a("W9HT")),u=(a("14J3"),a("BMrR")),m=(a("jCWc"),a("kPKH")),s=(a("1YHl"),a("VNzZ")),d=a("fWQN"),g=a("mtLc"),f=a("yKVA"),b=a("879j"),p=a("q1tI"),j=a.n(p),v=a("9kvl"),k=a("+ryr"),y=a("uJMD"),T=a("i0ey"),O=a("7pZ8"),Y=a.n(O),E=a("PW4v"),h=a("uyCD"),D=a("dYgQ"),L=a.n(D),w=(n=Object(v["a"])((function(e){var t=e.errorLog,a=e.global,n=e.loading;return{errorLog:t,global:a,loading:n.models.errorLog}})),n((o=function(e){Object(f["a"])(a,e);var t=Object(b["a"])(a);function a(e){var n;return Object(d["a"])(this,a),n=t.call(this,e),n.componentAuthority=Y.a.errorLog.get,n.buildInitialValues=function(e,t,a,n){var r={};return null!=e&&(r[T["b"].channelNote.name]=e.channelNote||"",r[T["b"].createTime.name]=Object(y["l"])(e.createTime,"YYYY-MM-DD HH:mm")||"",r[T["b"].updateTime.name]=Object(y["l"])(e.updateTime,"YYYY-MM-DD HH:mm")||""),r},n.getFormLayout=function(){return"horizontal"},n.renderBasicInfoTitleText=function(){return"\u5806\u6808\u4fe1\u606f"},n.formContent=function(){var e=n.state,t=e.dataLoading,a=e.processing,r=e.metaData,o=null==r?[]:r.stackTraceJson||[];return o=o.map((function(e,t){var a={};return a.key="stackTraceJsonItem_".concat(t),a.data=e,a})),j.a.createElement(j.a.Fragment,null,j.a.createElement(l["a"],{title:n.renderBasicInfoTitle(),className:L.a.card,bordered:!1,extra:j.a.createElement(s["a"],{offsetTop:20},j.a.createElement("div",null,n.renderRefreshButton()))},j.a.createElement(i["a"],{delay:500,spinning:t||a},j.a.createElement(u["a"],{gutter:24},null==r?null:j.a.createElement(j.a.Fragment,null,o.map((function(e){return j.a.createElement(m["a"],{key:e.key,span:24},j.a.createElement(k["a"],{language:"javascript"},JSON.stringify(e.data||"",null,"    ")))})))))))},n.state=Object(c["a"])(Object(c["a"])({},n.state),{loadApiPath:"errorLog/get"}),n}return Object(g["a"])(a,null,[{key:"getDerivedStateFromProps",value:function(e,t){return Object(y["n"])(e,t,{id:""},h["b"])}}]),a}(E["a"]),r=o))||r);t["default"]=w},dYgQ:function(e,t,a){e.exports={card:"antd-pro-pages-error-log-edit-stack-trace-info-index-card"}}}]);