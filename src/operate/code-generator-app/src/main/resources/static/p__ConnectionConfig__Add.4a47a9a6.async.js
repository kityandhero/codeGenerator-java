(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[12],{WmNs:function(e,a,t){e.exports={card:"antd-pro-pages-connection-config-add-index-card"}},dvFV:function(e,a,t){"use strict";t.r(a);var n,r,l,o=t("m25k"),m=(t("EsCq"),t("/5GD")),c=(t("Z2ky"),t("GOhW")),s=(t("bxD2"),t("FxU4")),i=(t("0GrD"),t("0qCI")),d=(t("ZM4h"),t("RLie")),f=(t("bckL"),t("oHOu")),p=(t("dvU4"),t("q69F")),u=t("Z6mO"),D=t("uf3W"),g=t("GkP1"),h=t("vVLA"),b=t("ZZRV"),E=t.n(b),C=t("9kvl"),F=t("uJMD"),x=t("i0ey"),P=t("7pZ8"),y=t.n(P),S=t("eVFP"),T=t("ShOv"),v=t("WmNs"),I=t.n(v),w=(n=Object(C["a"])((function(e){var a=e.connectionConfig,t=e.global,n=e.loading;return{connectionConfig:a,global:t,loading:n.models.connectionConfig}})),n((l=function(e){Object(g["a"])(t,e);var a=Object(h["a"])(t);function t(e){var n;return Object(u["a"])(this,t),n=a.call(this,e),n.componentAuthority=y.a.connectionConfig.add,n.buildInitialValues=function(){var e={};return Object(F["c"])()?(e[T["fieldData"].name.name]="test",e[T["fieldData"].databaseType.name]="".concat(100),e[T["fieldData"].host.name]="127.0.0.1",e[T["fieldData"].port.name]=3306,e[T["fieldData"].userName.name]="root",e[T["fieldData"].password.name]="root",e[T["fieldData"].schema.name]="test",e[T["fieldData"].encoding.name]="".concat(100)):e[T["fieldData"].port.name]=0,e[x["c"].createTime.name]=Object(F["m"])(new Date,"YYYY-MM-DD HH:mm"),e},n.getApiData=function(e){var a=e.connectionConfig.data;return a},n.supplementSubmitRequestParams=function(e){var a=e,t=n.state.selectConnectionType;return a.connectionType=t,a},n.afterSubmitSuccess=function(e,a,t,n,r){requestAnimationFrame((function(){p["a"].success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u4fdd\u5b58\u6210\u529f\uff0c\u8bf7\u8fdb\u884c\u4e0b\u4e00\u6b65\u64cd\u4f5c\u3002"})}));var l=e.connectionConfigId,o={pathname:"/connectionConfig/edit/load/".concat(l,"/key/basicInfo")};C["d"].replace(o)},n.onConnectionTypeChange=function(e){n.setState({selectConnectionType:e?T["connectionType"].SSH:T["connectionType"].TCP_IP})},n.formContent=function(){var e=n.state,a=e.processing,t=e.selectConnectionType;return E.a.createElement(E.a.Fragment,null,E.a.createElement(c["a"],{title:"\u57fa\u672c\u4fe1\u606f",className:I.a.card,bordered:!1,extra:E.a.createElement(f["a"],{offsetTop:20},E.a.createElement("div",null,n.renderSaveButton()))},E.a.createElement(s["a"],{spinning:a},E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:12,md:12,sm:24,xs:24},n.renderFormInput(T["fieldData"].name.label,T["fieldData"].name.name,!0,T["fieldData"].name.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormDatabaseDatabaseTypeSelectSelect()),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumber(T["fieldData"].port.label,T["fieldData"].port.name,!1,T["fieldData"].port.helper))),E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:18,md:12,sm:24,xs:24},n.renderFormInput(T["fieldData"].host.label,T["fieldData"].host.name,!0,T["fieldData"].host.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInput(T["fieldData"].schema.label,T["fieldData"].schema.name,!0,T["fieldData"].schema.helper))),E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInput(T["fieldData"].userName.label,T["fieldData"].userName.name,!0,T["fieldData"].userName.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormPassword(T["fieldData"].password.label,T["fieldData"].password.name,!0,T["fieldData"].password.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormDatabaseEncodingSelect())))),E.a.createElement(c["a"],{title:"SSH\u4fe1\u606f",className:I.a.card,bodyStyle:t!==T["connectionType"].SSH?{padding:0}:{},bordered:!1,extra:E.a.createElement(E.a.Fragment,null,E.a.createElement(m["a"],{checkedChildren:"\u5f00",unCheckedChildren:"\u5173",defaultChecked:t===T["connectionType"].SSH,onChange:function(e){n.onConnectionTypeChange(e)}}))},E.a.createElement(s["a"],{spinning:a},t===T["connectionType"].SSH?E.a.createElement(E.a.Fragment,null,E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInput(T["fieldData"].sshHost.label,T["fieldData"].sshHost.name,!0,T["fieldData"].sshHost.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumber(T["fieldData"].sshPort.label,T["fieldData"].sshPort.name,!0,T["fieldData"].sshPort.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumber(T["fieldData"].localPort.label,T["fieldData"].localPort.name,!0,T["fieldData"].localPort.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInputNumber(T["fieldData"].remotePort.label,T["fieldData"].remotePort.name,!0,T["fieldData"].remotePort.helper))),E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormInput(T["fieldData"].sshUser.label,T["fieldData"].sshUser.name,!0,T["fieldData"].sshUser.helper)),E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFormPassword(T["fieldData"].sshPassword.label,T["fieldData"].sshPassword.name,!0,T["fieldData"].sshPassword.helper)))):null)),E.a.createElement(c["a"],{title:"\u63cf\u8ff0\u4fe1\u606f",className:I.a.card,bordered:!1},E.a.createElement(s["a"],{spinning:a},E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:24,md:24,sm:24,xs:24},n.renderFormTextArea(T["fieldData"].description.label,T["fieldData"].description.name,!1,T["fieldData"].description.helper))))),E.a.createElement(c["a"],{title:"\u5176\u4ed6\u4fe1\u606f",className:I.a.card,bordered:!1},E.a.createElement(s["a"],{spinning:a},E.a.createElement(i["a"],{gutter:24},E.a.createElement(d["a"],{lg:6,md:12,sm:24,xs:24},n.renderFromCreateTimeField())))))},n.state=Object(o["a"])(Object(o["a"])({},n.state),{pageName:"\u589e\u52a0\u6570\u636e\u8fde\u63a5",submitApiPath:"connectionConfig/addBasicInfo",selectConnectionType:T["connectionType"].TCP_IP}),n}return Object(D["a"])(t,null,[{key:"getDerivedStateFromProps",value:function(e,a){return Object(F["o"])(e,a)}}]),t}(S["a"]),r=l))||r);a["default"]=w}}]);