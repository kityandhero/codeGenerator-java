(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[21],{A9O3:function(e,a,t){"use strict";t.r(a);var l,r,n,m=t("XEnU"),o=(t("IzEo"),t("bx4M")),s=(t("T2oS"),t("W9HT")),i=(t("14J3"),t("BMrR")),d=(t("jCWc"),t("kPKH")),c=(t("1YHl"),t("VNzZ")),u=t("XKWP"),p=t("N7Kx"),g=t("bvJU"),b=t("XPR9"),D=t("kFHX"),F=t("q1tI"),E=t.n(F),f=t("9kvl"),y=t("uJMD"),x=t("i0ey"),I=t("7pZ8"),N=t.n(I),T=t("PW4v"),h=t("uyCD"),v=t("4DO9"),O=t("t/hT"),j=t.n(O),L=(l=Object(f["a"])((function(e){var a=e.errorLog,t=e.global,l=e.loading;return{errorLog:a,global:t,loading:l.models.errorLog}})),l((n=function(e){function a(e){var t;return Object(u["a"])(this,a),t=Object(g["a"])(this,Object(b["a"])(a).call(this,e)),t.componentAuthority=N.a.errorLog.get,t.buildInitialValues=function(e){var a={};return null!=e&&(a[x["a"].channelNote.name]=e.channelNote||"",a[x["a"].createTime.name]=Object(y["q"])(e.createTime,"YYYY-MM-DD HH:mm")||"",a[x["a"].updateTime.name]=Object(y["q"])(e.updateTime,"YYYY-MM-DD HH:mm")||""),a},t.getFormLayout=function(){return"horizontal"},t.formContent=function(){var e=t.state,a=e.dataLoading,l=e.processing,r=e.metaData;return E.a.createElement(E.a.Fragment,null,E.a.createElement(o["a"],{title:t.renderBasicInfoTitle(),className:j.a.card,bordered:!1,bodyStyle:{paddingBottom:0},extra:E.a.createElement(c["a"],{offsetTop:20},E.a.createElement("div",null,t.renderRefreshButton()))},E.a.createElement(s["a"],{spinning:a||l},E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{span:24},t.renderFormDisplayFormItem(v["fieldData"].message.label,null==r?"\u65e0":r.message||"\u65e0")),E.a.createElement(d["a"],{span:24},t.renderFormDisplayFormItem(v["fieldData"].causeMessage.label,null==r?"\u65e0":r.causeMessage||"\u65e0"))))),E.a.createElement(o["a"],{title:"\u5176\u4ed6\u4fe1\u606f",className:j.a.card,bordered:!1},E.a.createElement(s["a"],{spinning:a||l},E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{span:24},t.renderFormDisplayFormItem(v["fieldData"].uri.label,null==r?"\u65e0":r.uri||"\u65e0")),E.a.createElement(d["a"],{span:24},t.renderFormDisplayFormItem(v["fieldData"].exceptionTypeName.label,null==r?"\u65e0":r.exceptionTypeName||"\u65e0")),E.a.createElement(d["a"],{span:24},t.renderFormDisplayFormItem(v["fieldData"].scene.label,null==r?"\u65e0":r.scene||"\u65e0")),E.a.createElement(d["a"],{span:24},t.renderFormDisplayFormItem(v["fieldData"].otherLog.label,null==r?"\u65e0":r.otherLog||"\u65e0"))))),E.a.createElement(o["a"],{title:"\u5176\u4ed6\u4fe1\u606f",className:j.a.card,bordered:!1},E.a.createElement(s["a"],{spinning:a||l},E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(v["fieldData"].host.label,null==r?"\u65e0":r.host||"\u65e0")),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(v["fieldData"].port.label,null==r?"\u65e0 | \u9ed8\u8ba4":r.port||"\u65e0 | \u9ed8\u8ba4")),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(v["fieldData"].sendNotification.label,null==r||0===(r.sendNotification||0)?"\u5426":"\u662f")),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(v["fieldData"].sendResult.label,null==r||0===(r.sendResult||0)?"\u5426":"\u662f")),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(v["fieldData"].sendUnixTime.label,null==r||"0"==="".concat(r.sendUnixTime||0)?"\u65e0":r.sendUnixTime||"\u65e0")),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(v["fieldData"].degreeNote.label,null==r?"\u65e0":r.degreeNote||"\u65e0")),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(v["fieldData"].resolveNote.label,null==r?"\u65e0":r.resolveNote||"\u65e0")),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(x["a"].statusNote.label,null==r?"\u65e0":r.statusNote||"\u65e0")),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(x["a"].channelNote.label,null==r?"\u65e0":r.channelNote||"\u65e0")),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(x["a"].ip.label,null==r?"\u65e0":r.ip||"\u65e0")),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(x["a"].createTime.label,null==r?"\u65e0":r.createTime||"\u65e0")),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormDisplayFormItem(x["a"].updateTime.label,null==r?"\u65e0":r.updateTime||"\u65e0"))))))},t.state=Object(m["a"])({},t.state,{},{loadApiPath:"errorLog/get"}),t}return Object(D["a"])(a,e),Object(p["a"])(a,null,[{key:"getDerivedStateFromProps",value:function(e,a){return Object(y["r"])(e,a,{id:""},h["b"])}}]),a}(T["a"]),r=n))||r);a["default"]=L},"t/hT":function(e,a,t){e.exports={card:"antd-pro-pages-error-log-edit-basic-info-index-card"}}}]);