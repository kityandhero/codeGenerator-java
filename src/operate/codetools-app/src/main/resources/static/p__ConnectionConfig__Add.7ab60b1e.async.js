(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[6],{dvFV:function(e,t,a){"use strict";var l=a("mZ4U");Object.defineProperty(t,"__esModule",{value:!0}),t.default=void 0;var n=l(a("mK77"));a("DC98");var r=l(a("WIJn"));a("JE8u");var d=l(a("NMMB"));a("Y4LA");var o=l(a("SGa7"));a("0v4F");var u=l(a("VJ0+"));a("DZS5");var m=l(a("X9eP"));a("r6//");var i=l(a("Va1g"));a("HxWU");var c=l(a("ZhXv"));a("xIiZ");var f=l(a("VXX2"));a("nSme");var s,p,g,F=l(a("cjFt")),E=l(a("43Yg")),h=l(a("scpF")),b=l(a("O/V9")),D=l(a("8aBX")),H=l(a("+ufk")),I=a("LneV"),C=a("DWJA"),v=a("z6Ls"),x=a("uJMD"),y=l(a("7pZ8")),P=a("i0ey"),S=l(a("soTq")),T=a("/lRM"),w=a("ShOv"),N=l(a("tUt5")),R=(s=(0,I.connect)(function(e){var t=e.connectionConfig,a=e.global,l=e.loading;return{connectionConfig:t,global:a,loading:l.models.connectionConfig}}),s((g=function(e){function t(e){var a;return(0,E.default)(this,t),a=(0,h.default)(this,(0,b.default)(t).call(this,e)),a.componentAuthority=y.default.connectionConfig.add,a.formRef=H.default.createRef(),a.getApiData=function(e){var t=e.connectionConfig.data;return t},a.supplementSubmitRequestParams=function(e){var t=e,l=a.state.selectConnectionType;return t.connectionType=l,t},a.afterSubmitSuccess=function(e,t,l,n,r){var d=a.props.dispatch;requestAnimationFrame(function(){F.default.success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u4fdd\u5b58\u6210\u529f\uff0c\u8bf7\u8fdb\u884c\u4e0b\u4e00\u6b65\u64cd\u4f5c\u3002"})});var o=e.connectionConfigId,u={pathname:"/connectionConfig/edit/load/".concat(o,"/1/basicInfo")};d(C.routerRedux.replace(u))},a.onConnectionTypeChange=function(e){a.setState({selectConnectionType:e?w.connectionType.SSH:w.connectionType.TCP_IP})},a.getTargetForm=function(){return a.formRef.current},a.formContent=function(){var e=a.state,t=e.processing,l=e.selectConnectionType,n={};return n[T.customFieldCollection.databaseType.name]=null,n[T.customFieldCollection.databaseEncoding.name]=null,n[P.constants.createTime.name]=(0,x.formatDatetime)(new Date,"YYYY-MM-DD HH:mm"),H.default.createElement("div",{className:N.default.containorBox},H.default.createElement(r.default,{ref:a.formRef,initialValues:n,layout:"vertical"},H.default.createElement(o.default,{title:"\u57fa\u672c\u4fe1\u606f",className:N.default.card,bordered:!1,extra:H.default.createElement(c.default,{offsetTop:20},H.default.createElement(H.default.Fragment,null,H.default.createElement(f.default,{type:"primary",icon:H.default.createElement(v.SaveOutlined,null),disabled:t,onClick:function(e){a.validate(e)},loading:t},"\u4fdd\u5b58")))},H.default.createElement(u.default,{spinning:t},H.default.createElement(m.default,{gutter:24},H.default.createElement(i.default,{lg:18,md:12,sm:24,xs:24},a.renderFormInputFormItem(w.fieldData.name,"name",!0,(0,x.buildFieldHelper)(w.fieldData.nameHelper))),H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormDatabaseTypeSelectFormItem())),H.default.createElement(m.default,{gutter:24},H.default.createElement(i.default,{lg:18,md:12,sm:24,xs:24},a.renderFormInputFormItem(w.fieldData.host,"host",!0,(0,x.buildFieldHelper)(w.fieldData.hostHelper))),H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormInputNumberFormItem(w.fieldData.port,"port",!0,(0,x.buildFieldHelper)(w.fieldData.portHelper)))),H.default.createElement(m.default,{gutter:24},H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormInputFormItem(w.fieldData.userName,"userName",!0,(0,x.buildFieldHelper)(w.fieldData.userNameHelper))),H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormPasswordFormItem(w.fieldData.password,"password",!0,(0,x.buildFieldHelper)(w.fieldData.passwordHelper))),H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormInputFormItem(w.fieldData.schema,"schema",!0,(0,x.buildFieldHelper)(w.fieldData.schemaHelper))),H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormDatabaseEncodingSelectFormItem())))),H.default.createElement(o.default,{title:"SSH\u4fe1\u606f",className:N.default.card,bodyStyle:l!==w.connectionType.SSH?{padding:0}:{},bordered:!1,extra:H.default.createElement(H.default.Fragment,null,H.default.createElement(d.default,{checkedChildren:"\u5f00",unCheckedChildren:"\u5173",defaultChecked:l===w.connectionType.SSH,onChange:function(e){a.onConnectionTypeChange(e)}}))},H.default.createElement(u.default,{spinning:t},l===w.connectionType.SSH?H.default.createElement(H.default.Fragment,null,H.default.createElement(m.default,{gutter:24},H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormInputFormItem(w.fieldData.sshHost,"sshHost",!0,(0,x.buildFieldHelper)(w.fieldData.sshHostHelper))),H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormInputNumberFormItem(w.fieldData.sshPort,"sshPort",!0,(0,x.buildFieldHelper)(w.fieldData.sshPortHelper))),H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormInputNumberFormItem(w.fieldData.localPort,"localPort",!0,(0,x.buildFieldHelper)(w.fieldData.localPortHelper))),H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormInputNumberFormItem(w.fieldData.remotePort,"remotePort",!0,(0,x.buildFieldHelper)(w.fieldData.remotePortHelper)))),H.default.createElement(m.default,{gutter:24},H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormInputFormItem(w.fieldData.sshUser,"sshUser",!0,(0,x.buildFieldHelper)(w.fieldData.sshUserHelper))),H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFormPasswordFormItem(w.fieldData.sshPassword,"sshPassword",!0,(0,x.buildFieldHelper)(w.fieldData.sshPasswordHelper))))):null)),H.default.createElement(o.default,{title:"\u5176\u4ed6\u4fe1\u606f",className:N.default.card,bordered:!1},H.default.createElement(u.default,{spinning:t},H.default.createElement(m.default,{gutter:24},H.default.createElement(i.default,{lg:6,md:12,sm:24,xs:24},a.renderFromCreateTimeField()))))))},a.state=(0,n.default)({},a.state,{pageName:"\u589e\u52a0\u6570\u636e\u8fde\u63a5",submitApiPath:"connectionConfig/addBasicInfo",selectConnectionType:w.connectionType.TCP_IP}),a}return(0,D.default)(t,e),t}(S.default),p=g))||p),k=R;t.default=k},tUt5:function(e,t,a){e.exports={containorBox:"antd-pro-pages-connection-config-add-index-containorBox",card:"antd-pro-pages-connection-config-add-index-card"}}}]);