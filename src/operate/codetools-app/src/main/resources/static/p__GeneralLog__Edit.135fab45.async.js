(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[12],{"0IYL":function(e,t,a){"use strict";var l=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=l(a("mK77"));a("ywkC");var r,u,d,o=l(a("oSez")),i=l(a("43Yg")),s=l(a("/tCh")),c=l(a("scpF")),f=l(a("O/V9")),m=l(a("8aBX")),g=l(a("ZZRV")),p=a("LneV"),v=a("uJMD"),b=l(a("7pZ8")),h=a("i0ey"),L=l(a("KNI+")),F=a("2R2y"),E=a("bDL1"),D=(r=(0,p.connect)(function(e){var t=e.generalLog,a=e.global,l=e.loading;return{generalLog:t,global:a,loading:l.models.generalLog}}),r((d=function(e){function t(e){var a;return(0,i.default)(this,t),a=(0,c.default)(this,(0,f.default)(t).call(this,e)),a.componentAuthority=b.default.generalLog.get,a.tabList=[{key:"basicInfo",show:a.checkAuthority(b.default.generalLog.get),tab:"\u57fa\u672c\u4fe1\u606f"}],a.getApiData=function(e){var t=e.generalLog.data;return t},a.checkNeedUpdate=function(e,t,l){return(0,F.checkNeedUpdateAssist)(a.state,e,t,l)},a.supplementLoadRequestParams=function(e){var t=e,l=a.state.generalLogId;return t.generalLogId=l,t},a.afterLoadSuccess=function(e,t,l,n){if(null!=(e||null)){var r=e||{message:""},u=r.message;a.setState({pageName:"".concat(E.fieldData.message.label,"\uff1a\uff1a").concat(u)})}},a.pageHeaderLogo=function(){var e=a.state.metaData;return g.default.createElement(o.default,{size:"large",src:null===e?"":e.imageUrl||"/noImageSmall.png"})},a.pageHeaderExtraContentData=function(){var e=a.state.metaData;return{textLabel:h.constants.statusNote.label,text:null===e?"":e.statusNote,timeLabel:h.constants.createTime.label,time:null===e?null:(0,v.toDatetime)(e.createTime)}},a.pageHeaderContentData=function(){var e=a.state.metaData,t=[];return t.push({label:E.fieldData.generalLogId.label,value:null===e?"":e.generalLogId||"",canCopy:!0}),t.push({label:h.constants.ip.label,value:null===e?"":e.ip||""}),t.push({label:h.constants.channelNote.label,value:null===e?"":e.channelNote||""}),t.push({label:h.constants.createTime.label,value:null===e?"":e.createTime||""}),t.push({label:h.constants.updateTime.label,value:null===e?"":e.createTime||""}),t},a.state=(0,n.default)({},a.state,{pageName:"".concat(E.fieldData.message.label,"\uff1a"),loadApiPath:"generalLog/get",backPath:"/generalLog/pageList/key",generalLogId:null}),a}return(0,m.default)(t,e),(0,s.default)(t,null,[{key:"getDerivedStateFromProps",value:function(e,t){return(0,v.getDerivedStateFromPropsForUrlParams)(e,t,{id:""},F.parseUrlParamsForSetState)}}]),t}(L.default),u=d))||u),y=D;t.default=y},"2R2y":function(e,t,a){"use strict";var l=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.parseUrlParamsForSetState=u,t.checkNeedUpdateAssist=d,t.empty=o;var n=l(a("Ico4")),r=l(a("UWy3"));function u(e){var t=e.urlParams,a=t.id;return{generalLogId:a}}function d(e,t,a,l){var n=e.generalLogId,r=a.generalLogId;return r!==n}function o(){return i.apply(this,arguments)}function i(){return i=(0,r.default)(n.default.mark(function e(){return n.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",{});case 1:case"end":return e.stop()}},e)})),i.apply(this,arguments)}},BGsf:function(e,t,a){"use strict";var l=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0,a("Bqa8");var n=l(a("sNGJ")),r=l(a("43Yg")),u=l(a("/tCh")),d=l(a("scpF")),o=l(a("O/V9")),i=l(a("8aBX")),s=a("uJMD"),c=l(a("U1dv")),f=a("2R2y"),m=function(e){function t(){var e,a;(0,r.default)(this,t);for(var l=arguments.length,u=new Array(l),i=0;i<l;i++)u[i]=arguments[i];return a=(0,d.default)(this,(e=(0,o.default)(t)).call.apply(e,[this].concat(u))),a.goToUpdateWhenProcessed=!0,a.getApiData=function(e){var t=e.generalLog.data;return t},a.checkNeedUpdate=function(e,t,l){return(0,f.checkNeedUpdateAssist)(a.state,e,t,l)},a.supplementLoadRequestParams=function(e){var t=e,l=a.state.generalLogId;return t.generalLogId=l,t},a.afterSubmitSuccess=function(e,t,a,l,r){requestAnimationFrame(function(){n.default.success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u4fdd\u5b58\u6210\u529f\uff0c\u8bf7\u8fdb\u884c\u540e\u7eed\u64cd\u4f5c\u3002"})})},a}return(0,i.default)(t,e),(0,u.default)(t,null,[{key:"getDerivedStateFromProps",value:function(e,t){return(0,s.getDerivedStateFromPropsForUrlParams)(e,t,{id:""},f.parseUrlParamsForSetState)}}]),t}(c.default),g=m;t.default=g},bCdQ:function(e,t,a){e.exports={hidden:"antd-pro-pages-general-log-edit-basic-info-index-hidden",help:"antd-pro-pages-general-log-edit-basic-info-index-help",containorBox:"antd-pro-pages-general-log-edit-basic-info-index-containorBox",card:"antd-pro-pages-general-log-edit-basic-info-index-card","ant-upload-select-picture-card":"antd-pro-pages-general-log-edit-basic-info-index-ant-upload-select-picture-card","ant-upload-text":"antd-pro-pages-general-log-edit-basic-info-index-ant-upload-text",mainImageBox:"antd-pro-pages-general-log-edit-basic-info-index-mainImageBox",mainImage:"antd-pro-pages-general-log-edit-basic-info-index-mainImage",mainImageAction:"antd-pro-pages-general-log-edit-basic-info-index-mainImageAction"}},zihs:function(e,t,a){"use strict";var l=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=l(a("mK77"));a("8BGC");var r=l(a("5Ace"));a("FJBW");var u=l(a("q/L9"));a("eU3K");var d=l(a("x1KI"));a("NdpQ");var o=l(a("hdUO"));a("l4mJ");var i=l(a("ET+S"));a("WIuS");var s=l(a("EdvG"));a("jlqO");var c,f,m,g=l(a("Hbi4")),p=l(a("43Yg")),v=l(a("/tCh")),b=l(a("scpF")),h=l(a("O/V9")),L=l(a("8aBX")),F=l(a("ZZRV")),E=a("LneV"),D=a("lD+o"),y=a("uJMD"),x=a("i0ey"),I=l(a("7pZ8")),N=l(a("Ul7h")),P=l(a("BGsf")),U=a("2R2y"),S=a("bDL1"),k=l(a("bCdQ")),T=(c=(0,E.connect)(function(e){var t=e.generalLog,a=e.global,l=e.loading;return{generalLog:t,global:a,loading:l.models.generalLog}}),c((m=function(e){function t(e){var a;return(0,p.default)(this,t),a=(0,b.default)(this,(0,h.default)(t).call(this,e)),a.componentAuthority=I.default.generalLog.get,a.formRef=F.default.createRef(),a.getTargetForm=function(){return a.formRef.current},a.afterLoadSuccess=function(e,t,l,n){var r={};r[x.constants.channelNote.name]=null===e?"":e.channelNote||"",r[x.constants.ip.name]=null===e?"":e.ip||"",r[x.constants.createTime.name]=null===e?"":(0,y.formatDatetime)(e.createTime,"YYYY-MM-DD HH:mm")||"",r[x.constants.updateTime.name]=null===e?"":(0,y.formatDatetime)(e.updateTime,"YYYY-MM-DD HH:mm")||"";var u=a.getTargetForm();u.setFieldsValue(r)},a.formContent=function(){var e=a.state,t=e.dataLoading,l=e.processing,n=e.metaData;return F.default.createElement(F.default.Fragment,null,F.default.createElement("div",{className:k.default.containorBox},F.default.createElement(d.default,{title:"\u57fa\u672c\u4fe1\u606f",className:k.default.card,bordered:!1,extra:F.default.createElement(F.default.Fragment,null,F.default.createElement(g.default,{type:"default",icon:F.default.createElement(D.ReloadOutlined,null),disabled:t||l,onClick:function(){a.reloadData()},loading:l},"\u5237\u65b0"))},F.default.createElement(o.default,{spinning:t||l},F.default.createElement(i.default,{gutter:24},F.default.createElement(s.default,{span:24},F.default.createElement(N.default,null,null===n?"":n.message||"\u65e0"))))),F.default.createElement(d.default,{title:F.default.createElement(F.default.Fragment,null,F.default.createElement(D.ContactsOutlined,null),F.default.createElement("span",{className:k.default.cardTitle},S.fieldData.content.label)),className:k.default.card,bordered:!1},F.default.createElement(o.default,{spinning:t||l},F.default.createElement(i.default,{gutter:24},F.default.createElement(s.default,{lg:24,md:12,sm:24},F.default.createElement(N.default,null,null===n?"":n.content||"\u65e0"))))),F.default.createElement(d.default,{title:"\u5176\u4ed6\u4fe1\u606f",className:k.default.card,bordered:!1},F.default.createElement(o.default,{spinning:t||l},F.default.createElement(u.default,{ref:a.formRef,layout:"vertical"},F.default.createElement(i.default,{gutter:24},F.default.createElement(s.default,{lg:6,md:12,sm:24,xs:24},a.renderFormInputFormItem(x.constants.ip.label,x.constants.ip.name,!0,(0,y.buildFieldHelper)(x.constants.ip.helper),F.default.createElement(D.FormOutlined,null),null,!1)),F.default.createElement(s.default,{lg:6,md:12,sm:24,xs:24},a.renderFormInputFormItem(x.constants.channelNote.label,x.constants.channelNote.name,!0,(0,y.buildFieldHelper)(x.constants.channelNote.helper),F.default.createElement(D.FormOutlined,null),null,!1)),F.default.createElement(s.default,{lg:6,md:12,sm:24,xs:24},a.renderFromCreateTimeField()),F.default.createElement(s.default,{lg:6,md:12,sm:24,xs:24},a.renderFromUpdateTimeField())))))),F.default.createElement(r.default,null))},a.state=(0,n.default)({},a.state,{loadApiPath:"generalLog/get"}),a}return(0,L.default)(t,e),(0,v.default)(t,null,[{key:"getDerivedStateFromProps",value:function(e,t){return(0,y.getDerivedStateFromPropsForUrlParams)(e,t,{id:""},U.parseUrlParamsForSetState)}}]),t}(P.default),f=m))||f),A=T;t.default=A}}]);