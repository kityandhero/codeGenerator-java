(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[4],{AAa7:function(e,a,t){"use strict";var l=t("mZ4U");Object.defineProperty(a,"__esModule",{value:!0}),a.parseUrlParamsForSetState=d,a.checkNeedUpdateAssist=u,a.empty=c;var n=l(t("Ico4")),r=l(t("UWy3"));function d(e){var a=e.urlParams,t=a.id;return{accessWayId:t}}function u(e,a,t,l){var n=e.accessWayId,r=t.accessWayId;return r!==n}function c(){return s.apply(this,arguments)}function s(){return s=(0,r.default)(n.default.mark(function e(){return n.default.wrap(function(e){while(1)switch(e.prev=e.next){case 0:return e.abrupt("return",{});case 1:case"end":return e.stop()}},e)})),s.apply(this,arguments)}},RQGW:function(e,a,t){e.exports={hidden:"antd-pro-pages-access-way-edit-basic-info-index-hidden",help:"antd-pro-pages-access-way-edit-basic-info-index-help",containorBox:"antd-pro-pages-access-way-edit-basic-info-index-containorBox",card:"antd-pro-pages-access-way-edit-basic-info-index-card","ant-upload-select-picture-card":"antd-pro-pages-access-way-edit-basic-info-index-ant-upload-select-picture-card","ant-upload-text":"antd-pro-pages-access-way-edit-basic-info-index-ant-upload-text",mainImageBox:"antd-pro-pages-access-way-edit-basic-info-index-mainImageBox",mainImage:"antd-pro-pages-access-way-edit-basic-info-index-mainImage",mainImageAction:"antd-pro-pages-access-way-edit-basic-info-index-mainImageAction"}},XTBs:function(e,a,t){"use strict";var l=t("mZ4U");Object.defineProperty(a,"__esModule",{value:!0}),a.default=void 0,t("Bqa8");var n=l(t("sNGJ")),r=l(t("43Yg")),d=l(t("/tCh")),u=l(t("scpF")),c=l(t("O/V9")),s=l(t("8aBX")),i=t("uJMD"),o=l(t("U1dv")),f=t("AAa7"),m=function(e){function a(){var e,t;(0,r.default)(this,a);for(var l=arguments.length,d=new Array(l),s=0;s<l;s++)d[s]=arguments[s];return t=(0,u.default)(this,(e=(0,c.default)(a)).call.apply(e,[this].concat(d))),t.goToUpdateWhenProcessed=!0,t.getApiData=function(e){var a=e.accessWay.data;return a},t.checkNeedUpdate=function(e,a,l){return(0,f.checkNeedUpdateAssist)(t.state,e,a,l)},t.supplementLoadRequestParams=function(e){var a=e,l=t.state.accessWayId;return a.accessWayId=l,a},t.afterSubmitSuccess=function(e,a,t,l,r){requestAnimationFrame(function(){n.default.success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u4fdd\u5b58\u6210\u529f\uff0c\u8bf7\u8fdb\u884c\u540e\u7eed\u64cd\u4f5c\u3002"})})},t}return(0,s.default)(a,e),(0,d.default)(a,null,[{key:"getDerivedStateFromProps",value:function(e,a){return(0,i.getDerivedStateFromPropsForUrlParams)(e,a,{id:""},f.parseUrlParamsForSetState)}}]),a}(o.default),p=m;a.default=p},b7Gq:function(e,a,t){"use strict";var l=t("mZ4U");Object.defineProperty(a,"__esModule",{value:!0}),a.default=void 0;var n=l(t("mK77"));t("ywkC");var r,d,u,c=l(t("oSez")),s=l(t("43Yg")),i=l(t("/tCh")),o=l(t("scpF")),f=l(t("O/V9")),m=l(t("8aBX")),p=l(t("ZZRV")),g=t("LneV"),v=t("uJMD"),b=l(t("7pZ8")),y=t("i0ey"),h=l(t("KNI+")),D=t("AAa7"),F=t("CM8b"),W=(r=(0,g.connect)(function(e){var a=e.accessWay,t=e.global,l=e.loading;return{accessWay:a,global:t,loading:l.models.accessWay}}),r((u=function(e){function a(e){var t;return(0,s.default)(this,a),t=(0,o.default)(this,(0,f.default)(a).call(this,e)),t.componentAuthority=b.default.accessWay.get,t.tabList=[{key:"basicInfo",show:t.checkAuthority(b.default.accessWay.get),tab:"\u57fa\u672c\u4fe1\u606f"}],t.getApiData=function(e){var a=e.accessWay.data;return a},t.checkNeedUpdate=function(e,a,l){return(0,D.checkNeedUpdateAssist)(t.state,e,a,l)},t.supplementLoadRequestParams=function(e){var a=e,l=t.state.accessWayId;return a.accessWayId=l,a},t.afterLoadSuccess=function(e,a,l,n){if(null!=(e||null)){var r=e||{name:""},d=r.name;t.setState({pageName:"".concat(F.fieldData.name.label,"\uff1a").concat(d)})}},t.pageHeaderLogo=function(){var e=t.state.metaData;return p.default.createElement(c.default,{size:"large",src:null===e?"":e.imageUrl||"/noImageSmall.png"})},t.pageHeaderExtraContentData=function(){var e=t.state.metaData;return{textLabel:y.constants.statusNote.label,text:null===e?"":e.statusNote,timeLabel:y.constants.createTime.label,time:null===e?null:(0,v.toDatetime)(e.createTime)}},t.pageHeaderContentData=function(){var e=t.state.metaData,a=[];return a.push({label:F.fieldData.accessWayId.label,value:null===e?"":e.accessWayId,canCopy:!0}),a.push({label:F.fieldData.tag.label,value:null===e?"":e.tag,canCopy:!1}),a.push({label:y.constants.channelNote.label,value:null===e?"":e.channelNote}),a.push({label:y.constants.createTime.label,value:null===e?"":e.createTime}),a.push({label:y.constants.updateTime.label,value:null===e?"":e.createTime}),a},t.state=(0,n.default)({},t.state,{pageName:"".concat(F.fieldData.name.label,"\uff1a"),loadApiPath:"accessWay/get",backPath:"/accessWay/pageList/key",accessWayId:null}),t}return(0,m.default)(a,e),(0,i.default)(a,null,[{key:"getDerivedStateFromProps",value:function(e,a){return(0,v.getDerivedStateFromPropsForUrlParams)(e,a,{id:""},D.parseUrlParamsForSetState)}}]),a}(h.default),d=u))||d),x=W;a.default=x},iUZ9:function(e,a,t){"use strict";var l=t("mZ4U");Object.defineProperty(a,"__esModule",{value:!0}),a.default=void 0;var n=l(t("mK77"));t("8BGC");var r=l(t("5Ace"));t("FJBW");var d=l(t("q/L9"));t("eU3K");var u=l(t("x1KI"));t("NdpQ");var c=l(t("hdUO"));t("l4mJ");var s=l(t("ET+S"));t("WIuS");var i=l(t("EdvG"));t("VAec");var o=l(t("8fja"));t("jlqO");var f,m,p,g=l(t("Hbi4")),v=l(t("43Yg")),b=l(t("/tCh")),y=l(t("scpF")),h=l(t("O/V9")),D=l(t("8aBX")),F=l(t("ZZRV")),W=t("LneV"),x=t("lD+o"),E=t("uJMD"),I=t("i0ey"),P=l(t("7pZ8")),A=l(t("XTBs")),U=t("AAa7"),S=t("CM8b"),w=l(t("RQGW")),T=(f=(0,W.connect)(function(e){var a=e.accessWay,t=e.global,l=e.loading;return{accessWay:a,global:t,loading:l.models.accessWay}}),f((p=function(e){function a(e){var t;return(0,v.default)(this,a),t=(0,y.default)(this,(0,h.default)(a).call(this,e)),t.componentAuthority=P.default.accessWay.get,t.formRef=F.default.createRef(),t.getTargetForm=function(){return t.formRef.current},t.afterLoadSuccess=function(e,a,l,n){var r={};r[S.fieldData.name.name]=null===e?"":e.name||"",r[S.fieldData.description.name]=null===e?"":e.description||"",r[S.fieldData.tag.name]=null===e?"":e.tag||"",r[S.fieldData.relativePath.name]=null===e?"":e.relativePath||"",r[I.constants.createTime.name]=null===e?"":(0,E.formatDatetime)(e.createTime,"YYYY-MM-DD HH:mm")||"",r[I.constants.updateTime.name]=null===e?"":(0,E.formatDatetime)(e.updateTime,"YYYY-MM-DD HH:mm")||"";var d=t.getTargetForm();d.setFieldsValue(r)},t.formContent=function(){var e=t.state,a=e.dataLoading,l=e.processing;return F.default.createElement(F.default.Fragment,null,F.default.createElement("div",{className:w.default.containorBox},F.default.createElement(d.default,{ref:t.formRef,layout:"vertical"},F.default.createElement(u.default,{title:"\u57fa\u672c\u4fe1\u606f",className:w.default.card,bordered:!1,extra:F.default.createElement(o.default,{offsetTop:20},F.default.createElement("div",null,F.default.createElement(g.default,{type:"default",icon:F.default.createElement(x.ReloadOutlined,null),disabled:a||l,onClick:function(){t.reloadData()},loading:l},"\u5237\u65b0")))},F.default.createElement(c.default,{spinning:a||l},F.default.createElement(s.default,{gutter:24},F.default.createElement(i.default,{lg:12,md:12,sm:24,xs:24},t.renderFormInputFormItem(S.fieldData.name.label,S.fieldData.name.name,!0,(0,E.buildFieldHelper)(S.fieldData.name.helper),F.default.createElement(x.FormOutlined,null),null,!1)),F.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(S.fieldData.tag.label,S.fieldData.tag.name,!0,(0,E.buildFieldHelper)(S.fieldData.tag.helper),F.default.createElement(x.FormOutlined,null),null,!1)),F.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},t.renderFormInputNumberFormItem(S.fieldData.relativePath.label,S.fieldData.relativePath.name,!0,(0,E.buildFieldHelper)(S.fieldData.relativePath.helper),null,!1))))),F.default.createElement(u.default,{title:"\u5176\u4ed6\u4fe1\u606f",className:w.default.card,bordered:!1},F.default.createElement(c.default,{spinning:a||l},F.default.createElement(s.default,{gutter:24},F.default.createElement(i.default,{span:24},t.renderFormTextAreaFormItem(S.fieldData.description.label,S.fieldData.description.name,!1,(0,E.buildFieldHelper)(S.fieldData.description.helper),null,!1))))),F.default.createElement(u.default,{title:"\u5176\u4ed6\u4fe1\u606f",className:w.default.card,bordered:!1},F.default.createElement(c.default,{spinning:a||l},F.default.createElement(s.default,{gutter:24},F.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},t.renderFromCreateTimeField()),F.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},t.renderFromUpdateTimeField())))))),F.default.createElement(r.default,null))},t.state=(0,n.default)({},t.state,{loadApiPath:"accessWay/get"}),t}return(0,D.default)(a,e),(0,b.default)(a,null,[{key:"getDerivedStateFromProps",value:function(e,a){return(0,E.getDerivedStateFromPropsForUrlParams)(e,a,{id:""},U.parseUrlParamsForSetState)}}]),a}(A.default),m=p))||m),N=T;a.default=N}}]);