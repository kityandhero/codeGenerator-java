(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[8],{"Nv+D":function(e,t,n){"use strict";var a=n("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var l=a(n("mK77"));n("gYhO");var o=a(n("3BvE"));n("Q1sh");var i=a(n("UF7A"));n("HY3Y");var r=a(n("i8fi"));n("5mFY");var c=a(n("K8BH"));n("Yg2Q");var d=a(n("PO0K"));n("0pur");var u=a(n("SP3O"));n("LT06");var f=a(n("6w8L"));n("KGFB");var m=a(n("nmb3")),s=a(n("43Yg")),g=a(n("scpF")),p=a(n("O/V9")),h=a(n("Tfcp")),b=a(n("8aBX"));n("E7Jk");var D,E,v,C=a(n("TiwC")),y=a(n("+ufk")),F=n("LneV"),I=n("DWJA"),k=n("z6Ls"),T=n("uJMD"),w=a(n("7pZ8")),x=n("ydnR"),S=n("i0ey"),O=a(n("zmXK")),A=a(n("lLw4")),B=a(n("paK7")),Y=n("ShOv"),L=C.default.confirm,N=(D=(0,F.connect)(function(e){var t=e.connectionConfig,n=e.global,a=e.loading;return{connectionConfig:t,global:n,loading:a.models.connectionConfig}}),D((v=function(e){function t(e){var n;return(0,s.default)(this,t),n=(0,g.default)(this,(0,p.default)(t).call(this,e)),n.componentAuthority=w.default.connectionConfig.list,n.getApiData=function(e){var t=e.connectionConfig.data;return t},n.handleItem=function(e,t){var a=n.state.metaOriginalData,l=-1;a.list.forEach(function(t,n){var a=t.connectionId;a===e&&(l=n)}),l>=0&&(a.list[l]=t(a.list[l]),n.setState({metaOriginalData:a}))},n.removeConfirm=function(e){var t=(0,h.default)(n),a=t.state.processing;L({title:"\u5220\u9664\u6570\u636e\u8fde\u63a5",content:"\u786e\u5b9a\u8981\u5220\u9664\u6570\u636e\u8fde\u63a5 ".concat(null==e?"":e.title," \u5417\uff1f"),okText:"\u786e\u5b9a",okType:"danger",cancelText:"\u53d6\u6d88",confirmLoading:{processing:a},onOk:function(){t.remove(e)},onCancel:function(){m.default.info("\u53d6\u6d88\u4e86\u5220\u9664\u64cd\u4f5c\uff01")}})},n.remove=function(e){var t=n.props.dispatch,a=(0,T.pretreatmentRequestParams)({},function(t){var n=t;return n.connectionId=e.connectionId,n});n.setState({processing:!0}),t({type:"connectionConfig/remove",payload:a}).then(function(){var e=n.props.connectionConfig.data,t=e.dataSuccess;t&&(requestAnimationFrame(function(){f.default.success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u5220\u9664\u6210\u529f\uff0c\u8bf7\u8fdb\u884c\u540e\u7eed\u64cd\u4f5c\u3002"})}),n.setState({processing:!1}),n.reloadData())})},n.goToAdd=function(){var e=n.props.dispatch,t={pathname:"/connectionConfig/add"};e(I.routerRedux.push(t))},n.goToEdit=function(e){var t=n.props.dispatch,a=e.connectionConfigId,l={pathname:"/connectionConfig/edit/load/".concat(a,"/key/basicInfo")};t(I.routerRedux.push(l))},n.handleMenuClick=function(e,t){var a=e.key;switch(a){case"delete":n.removeConfirm(t);break;case"openDatabase":n.openDatabase(t);break;default:break}},n.openDatabase=function(e){var t=n.props.dispatch,a=e.connectionConfigId;n.setState({processing:!0}),t({type:"connectionConfig/openDatabase",payload:{connectionConfigId:a}}).then(function(){var t=n.props.connectionConfig.data,a=t.dataSuccess;a&&requestAnimationFrame(function(){f.default.success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u5373\u5c06\u6253\u5f00\u6570\u636e\u8fde\u63a5 \u201c".concat(e.name,"\u201d")})}),n.setState({processing:!1})})},n.renderBatchActionMenu=function(){return[{key:"batchDelete",name:"\u6279\u91cf\u5220\u9664",icon:y.default.createElement(k.DeleteOutlined,null)}]},n.onBatchActionSelect=function(e){switch(e){case"batchDelete":n.onBatchDelete();break;default:break}},n.onBatchDelete=function(){m.default.info("onBatchDelete")},n.renderSimpleFormInitialValues=function(){var e={};return e[Y.fieldData.encoding.name]=x.unlimitedWithStringFlag.flag,e[Y.fieldData.databaseType.name]=x.unlimitedWithStringFlag.flag,e},n.renderSimpleFormRow=function(){return y.default.createElement(y.default.Fragment,null,y.default.createElement(d.default,{gutter:24},y.default.createElement(u.default,{lg:6,md:12,sm:24,xs:24},n.renderSearchInputFormItem(Y.fieldData.name.label,Y.fieldData.name.name,(0,T.buildFieldHelper)("\u4f9d\u636e\u540d\u79f0\u8fdb\u884c\u68c0\u7d22"))),y.default.createElement(u.default,{lg:6,md:12,sm:24,xs:24},n.renderSearchDatabaseEncodingFormItem(!0)),y.default.createElement(u.default,{lg:6,md:12,sm:24,xs:24},n.renderSearchDatabaseDatabaseTypeFormItem(!0)),n.renderSimpleFormButton()))},n.renderExtraAction=function(){var e=n.state,t=e.dataLoading,a=e.processing;return n.checkAuthority(w.default.connectionConfig.add)?y.default.createElement(y.default.Fragment,null,y.default.createElement(c.default,{type:"vertical"}),y.default.createElement(r.default,{key:"buttonPlus",disabled:t||a,type:"primary",icon:y.default.createElement(k.PlusOutlined,null),onClick:n.goToAdd},"\u65b0\u589e")):null},n.getColumn=function(){return[{title:Y.fieldData.name.label,dataIndex:Y.fieldData.name.name,align:"left",render:function(e){return y.default.createElement(y.default.Fragment,null,e||"--")}},{title:Y.fieldData.schema.label,dataIndex:Y.fieldData.schema.name,width:200,align:"center",render:function(e){return y.default.createElement(y.default.Fragment,null,e||"--")}},{title:Y.fieldData.databaseType.label,dataIndex:Y.fieldData.databaseType.name,width:120,align:"center",render:function(e){return y.default.createElement(y.default.Fragment,null,y.default.createElement(A.default,{tooltip:!0,lines:1},n.getDatabaseDatabaseTypeName("".concat(e||""))))}},{title:Y.fieldData.connectionType.label,dataIndex:Y.fieldData.connectionType.name,width:120,align:"center",render:function(e){return y.default.createElement(y.default.Fragment,null,y.default.createElement(A.default,{tooltip:!0,lines:1},n.getDatabaseConnectionTypeName("".concat(e||""))))}},{title:Y.fieldData.encoding.label,dataIndex:Y.fieldData.encoding.name,width:120,align:"center",render:function(e){return y.default.createElement(y.default.Fragment,null,y.default.createElement(A.default,{tooltip:!0,lines:1},n.getDatabaseEncodingName("".concat(e||""))))}},{title:Y.fieldData.connectionConfigId.label,dataIndex:Y.fieldData.connectionConfigId.name,width:120,align:"center",render:function(e){return y.default.createElement(y.default.Fragment,null,y.default.createElement(B.default,{tooltip:!0,lines:1,removeChildren:!0,extraContent:y.default.createElement(y.default.Fragment,null,y.default.createElement("a",{onClick:function(){(0,T.copyToClipboard)(e)}},(0,T.replaceTargetText)(e,"***",2,6)))},e," [\u70b9\u51fb\u590d\u5236]"))}},{title:S.constants.channelNote.label,dataIndex:S.constants.channelNote.name,width:160,align:"center",render:function(e,t){return y.default.createElement(y.default.Fragment,null,y.default.createElement(A.default,{tooltip:!0,lines:1},t.channelNote))}},{title:S.constants.createTime.label,dataIndex:S.constants.createTime.name,width:140,align:"center",sorter:!1,render:function(e){return y.default.createElement(y.default.Fragment,null,y.default.createElement(A.default,{tooltip:!0,lines:1},""===(e||"")?"--":(0,T.formatDatetime)((0,T.toDatetime)(e),"YYYY-MM-DD HH:mm")))}},{title:S.constants.customOperate.label,dataIndex:S.constants.customOperate.name,width:106,fixed:"right",align:"center",render:function(e,t){return y.default.createElement(y.default.Fragment,null,y.default.createElement(o.default.Button,{size:"small",onClick:function(){return n.goToEdit(t)},overlay:y.default.createElement(i.default,{onClick:function(e){return n.handleMenuClick(e,t)}},y.default.createElement(i.default.Item,{key:"openDatabase"},y.default.createElement(k.DatabaseOutlined,null),"\u8fde\u63a5\u6570\u636e\u5e93"),y.default.createElement(i.default.Item,{key:"delete"},y.default.createElement(k.DeleteOutlined,null),"\u5220\u9664\u8fde\u63a5"))},y.default.createElement(k.EditOutlined,null),"\u4fee\u6539"))}}]},n.state=(0,l.default)({},n.state,{pageName:"\u6570\u636e\u8fde\u63a5\u5217\u8868",paramsKey:"938bdc77-66b5-4afe-835b-9aa64a7ead5b",loadApiPath:"connectionConfig/pageList",showSelect:!0}),n}return(0,b.default)(t,e),t}(O.default),E=v))||E),K=N;t.default=K}}]);