(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[16],{NcRY:function(e,a){e.exports={fieldData:{databaseGeneratorConfigId:{name:"databaseGeneratorConfigId",label:"\u6570\u636e\u6807\u8bc6",helper:"\u6570\u636e\u6807\u8bc6\uff0c\u4e0d\u53ef\u66f4\u6539"},connectionConfigId:{name:"connectionConfigId",label:"\u8fde\u63a5\u6807\u8bc6",helper:"\u6570\u636e\u8fde\u63a5\u6807\u8bc6\uff0c\u4e0d\u53ef\u66f4\u6539"},connectorJarFile:{name:"connectorJarFile",label:"\u6570\u636e\u8fde\u63a5\u5668",helper:"\u6570\u636e\u8fde\u63a5\u7684\u8fde\u63a5\u5668"},projectFolder:{name:"projectFolder",label:"\u9879\u76ee\u6587\u4ef6\u5939",helper:"\u751f\u6210\u7684\u9879\u76ee\u6587\u4ef6\u5939"},modelPackage:{name:"modelPackage",label:"modelPackage",helper:"\u751f\u6210\u7684modelPackage"},modelTargetFolder:{name:"modelTargetFolder",label:"model\u6587\u4ef6\u5939",helper:"\u751f\u6210\u7684model\u7684\u6587\u4ef6\u5939"},daoPackage:{name:"daoPackage",label:"daoPackage",helper:"\u751f\u6210\u7684daoPackage"},daoTargetFolder:{name:"daoTargetFolder",label:"dao\u6587\u4ef6\u5939",helper:"\u751f\u6210\u7684dao\u7684\u6587\u4ef6\u5939"},mappingXmlPackage:{name:"mappingXmlPackage",label:"mappingXmlPackage",helper:"\u751f\u6210\u7684mappingXmlPackage"},mappingXmlTargetFolder:{name:"mappingXmlTargetFolder",label:"mappingXml\u6587\u4ef6\u5939",helper:"\u751f\u6210\u7684mappingXml\u7684\u6587\u4ef6\u5939"},offsetLimit:{name:"offsetLimit",label:"offsetLimit",helper:"offsetLimit"},needToStringHashCodeEquals:{name:"needToStringHashCodeEquals",label:"needToStringHashCodeEquals",helper:"needToStringHashCodeEquals"},needForUpdate:{name:"needForUpdate",label:"needForUpdate",helper:"needForUpdate"},annotationDAO:{name:"annotationDAO",label:"annotationDAO",helper:"annotationDAO"},annotation:{name:"annotation",label:"annotation",helper:"annotation"},encoding:{name:"encoding",label:"encoding",helper:"encoding"},useDAOExtendStyle:{name:"useDAOExtendStyle",label:"useDAOExtendStyle",helper:"useDAOExtendStyle"},useSchemaPrefix:{name:"useSchemaPrefix",label:"\u4f7f\u7528Schema\u524d\u7f00",helper:"\u4f7f\u7528Schema\u524d\u7f00\u8fdb\u884cSql\u64cd\u4f5c"},jsr310Support:{name:"jsr310Support",label:"jsr310Support",helper:"jsr310Support"},overrideXML:{name:"overrideXML",label:"overrideXML",helper:"overrideXML"},autoDelimitKeywords:{name:"autoDelimitKeywords",label:"\u81ea\u52a8\u8bc6\u522b\u6570\u636e\u5e93\u5173\u952e\u5b57",helper:"\u81ea\u52a8\u8bc6\u522b\u6570\u636e\u5e93\u5173\u952e\u5b57\uff0c\u9ed8\u8ba4false\uff0c\u5982\u679c\u8bbe\u7f6e\u4e3atrue\uff0c\u6839\u636eSqlReservedWords\u4e2d\u5b9a\u4e49\u7684\u5173\u952e\u5b57\u5217\u8868\u3002"},comment:{name:"comment",label:"\u4f7f\u7528\u6570\u636e\u5e93\u5907\u6ce8",helper:"\u751f\u6210\u65f6\u643a\u5e26\u6570\u636e\u5e93\u5907\u6ce8\u4fe1\u606f"}}}},fLrc:function(e,a,t){"use strict";t.r(a);var n,r,l,o=t("XEnU"),m=(t("IzEo"),t("bx4M")),d=(t("T2oS"),t("W9HT")),i=(t("14J3"),t("BMrR")),c=(t("jCWc"),t("kPKH")),s=(t("1YHl"),t("VNzZ")),f=(t("/zsF"),t("PArb")),g=(t("/xke"),t("TeRw")),p=t("XKWP"),D=t("N7Kx"),u=t("bvJU"),F=t("XPR9"),h=t("kFHX"),b=t("q1tI"),I=t.n(b),S=t("9kvl"),E=t("Fvcw"),x=t("uJMD"),P=t("ydnR"),T=t("7pZ8"),C=t.n(T),k=t("i0ey"),X=t("9HkY"),O=t("/JhO"),j=t("NcRY"),v=t("kWdi"),A=t.n(v),y=(n=Object(S["a"])((function(e){var a=e.databaseGeneratorConfig,t=e.global,n=e.loading;return{databaseGeneratorConfig:a,global:t,loading:n.models.databaseGeneratorConfig}})),n((l=function(e){function a(e){var t;return Object(p["a"])(this,a),t=Object(u["a"])(this,Object(F["a"])(a).call(this,e)),t.componentAuthority=C.a.databaseGeneratorConfig.get,t.buildInitialValues=function(e){var a={};return null!=e&&(a[j["fieldData"].databaseGeneratorConfigId.name]=e.databaseGeneratorConfigId||P["zeroInt"],a[j["fieldData"].connectionConfigId.name]=e.connectionConfigId||P["zeroInt"],a[j["fieldData"].connectorJarFile.name]=e.connectorJarFile||"",a[j["fieldData"].projectFolder.name]=e.projectFolder||"",a[j["fieldData"].modelPackage.name]=e.modelPackage||"",a[j["fieldData"].modelTargetFolder.name]=e.modelTargetFolder||"",a[j["fieldData"].daoPackage.name]=e.daoPackage||"",a[j["fieldData"].daoTargetFolder.name]=e.daoTargetFolder||"",a[j["fieldData"].mappingXmlPackage.name]=e.mappingXmlPackage||"",a[j["fieldData"].mappingXmlTargetFolder.name]=e.mappingXmlTargetFolder||"",a[j["fieldData"].encoding.name]="".concat(e.encoding||P["zeroInt"]),a[j["fieldData"].offsetLimit.name]="".concat(e.offsetLimit||P["zeroInt"]),a[j["fieldData"].needToStringHashCodeEquals.name]="".concat(e.needToStringHashCodeEquals||P["zeroInt"]),a[j["fieldData"].needForUpdate.name]="".concat(e.needForUpdate||P["zeroInt"]),a[j["fieldData"].annotationDAO.name]="".concat(e.annotationDAO||P["zeroInt"]),a[j["fieldData"].annotation.name]="".concat(e.annotation||P["zeroInt"]),a[j["fieldData"].useDAOExtendStyle.name]="".concat(e.useDAOExtendStyle||P["zeroInt"]),a[j["fieldData"].useSchemaPrefix.name]="".concat(e.useSchemaPrefix||P["zeroInt"]),a[j["fieldData"].jsr310Support.name]="".concat(e.jsr310Support||P["zeroInt"]),a[j["fieldData"].overrideXML.name]="".concat(e.overrideXML||P["zeroInt"]),a[j["fieldData"].autoDelimitKeywords.name]="".concat(e.autoDelimitKeywords||P["zeroInt"]),a[j["fieldData"].comment.name]="".concat(e.comment||P["zeroInt"]),a[k["a"].createTime.name]=Object(x["q"])(e.createTime,"YYYY-MM-DD HH:mm")||"",a[k["a"].updateTime.name]=Object(x["q"])(e.updateTime,"YYYY-MM-DD HH:mm")||""),a},t.getApiData=function(e){var a=e.databaseGeneratorConfig.data;return a},t.supplementLoadRequestParams=function(e){var a=e,n=t.state.connectionConfigId;return a.connectionConfigId=n,a},t.supplementSubmitRequestParams=function(e){var a=e,n=t.state.metaData,r=n.databaseGeneratorConfigId,l=n.connectionConfigId;return a.connectionConfigId=l,a.databaseGeneratorConfigId=r||"",a},t.afterSubmitSuccess=function(e,a,n,r,l){t.setState({metaData:e}),t.fillForm(e),requestAnimationFrame((function(){g["a"].success({placement:"bottomRight",message:"\u64cd\u4f5c\u7ed3\u679c",description:"\u6570\u636e\u5df2\u7ecf\u4fdd\u5b58\u6210\u529f\uff0c\u8bf7\u8fdb\u884c\u540e\u7eed\u64cd\u4f5c\u3002"})}))},t.formContent=function(){var e=t.state,a=e.dataLoading,n=e.processing;return I.a.createElement(I.a.Fragment,null,I.a.createElement(m["a"],{title:t.renderBasicInfoTitle(),className:A.a.card,bordered:!1,extra:I.a.createElement(s["a"],{offsetTop:20},I.a.createElement("div",null,t.renderRefreshButton(),I.a.createElement(f["a"],{type:"vertical"}),t.renderSaveButton()))},I.a.createElement(d["a"],{spinning:a||n},I.a.createElement(i["a"],{gutter:24},I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(j["fieldData"].connectionConfigId.label,j["fieldData"].connectionConfigId.name,!0,j["fieldData"].connectionConfigId.helper,I.a.createElement(E["a"],null),{},!1)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(j["fieldData"].connectorJarFile.label,j["fieldData"].connectorJarFile.name,!0,j["fieldData"].connectorJarFile.helper,I.a.createElement(E["a"],null),{},!1)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(j["fieldData"].projectFolder.label,j["fieldData"].projectFolder.name,!0,j["fieldData"].projectFolder.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(j["fieldData"].modelPackage.label,j["fieldData"].modelPackage.name,!0,j["fieldData"].modelPackage.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(j["fieldData"].modelTargetFolder.label,j["fieldData"].modelTargetFolder.name,!0,j["fieldData"].modelTargetFolder.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(j["fieldData"].daoPackage.label,j["fieldData"].daoPackage.name,!0,j["fieldData"].daoPackage.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(j["fieldData"].daoTargetFolder.label,j["fieldData"].daoTargetFolder.name,!0,j["fieldData"].daoTargetFolder.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(j["fieldData"].mappingXmlPackage.label,j["fieldData"].mappingXmlPackage.name,!0,j["fieldData"].mappingXmlPackage.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormInputFormItem(j["fieldData"].mappingXmlTargetFolder.label,j["fieldData"].mappingXmlTargetFolder.name,!0,j["fieldData"].mappingXmlTargetFolder.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormFileEncodingSelectFormItem()),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormWhetherSelectFormItem(j["fieldData"].useSchemaPrefix.label,j["fieldData"].useSchemaPrefix.name,j["fieldData"].useSchemaPrefix.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormWhetherSelectFormItem(j["fieldData"].offsetLimit.label,j["fieldData"].offsetLimit.name,j["fieldData"].offsetLimit.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormWhetherSelectFormItem(j["fieldData"].needToStringHashCodeEquals.label,j["fieldData"].needToStringHashCodeEquals.name,j["fieldData"].needToStringHashCodeEquals.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormWhetherSelectFormItem(j["fieldData"].needForUpdate.label,j["fieldData"].needForUpdate.name,j["fieldData"].needForUpdate.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormWhetherSelectFormItem(j["fieldData"].annotationDAO.label,j["fieldData"].annotationDAO.name,j["fieldData"].annotationDAO.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormWhetherSelectFormItem(j["fieldData"].annotation.label,j["fieldData"].annotation.name,j["fieldData"].annotation.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormWhetherSelectFormItem(j["fieldData"].useDAOExtendStyle.label,j["fieldData"].useDAOExtendStyle.name,j["fieldData"].useDAOExtendStyle.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormWhetherSelectFormItem(j["fieldData"].jsr310Support.label,j["fieldData"].jsr310Support.name,j["fieldData"].jsr310Support.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormWhetherSelectFormItem(j["fieldData"].overrideXML.label,j["fieldData"].overrideXML.name,j["fieldData"].overrideXML.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormWhetherSelectFormItem(j["fieldData"].autoDelimitKeywords.label,j["fieldData"].autoDelimitKeywords.name,j["fieldData"].autoDelimitKeywords.helper)),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFormWhetherSelectFormItem(j["fieldData"].comment.label,j["fieldData"].comment.name,j["fieldData"].comment.helper))))),I.a.createElement(m["a"],{title:"\u5176\u4ed6\u4fe1\u606f",className:A.a.card,bordered:!1},I.a.createElement(d["a"],{spinning:a||n},I.a.createElement(i["a"],{gutter:24},I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFromCreateTimeField()),I.a.createElement(c["a"],{lg:6,md:12,sm:24,xs:24},t.renderFromUpdateTimeField())))))},t.state=Object(o["a"])({},t.state,{},{loadApiPath:"databaseGeneratorConfig/getByConnectionId",submitApiPath:"databaseGeneratorConfig/set",connectionConfigId:null}),t}return Object(h["a"])(a,e),Object(D["a"])(a,null,[{key:"getDerivedStateFromProps",value:function(e,a){return Object(x["r"])(e,a,{id:""},O["b"])}}]),a}(X["a"]),r=l))||r);a["default"]=y},kWdi:function(e,a,t){e.exports={card:"antd-pro-pages-connection-config-edit-database-generator-config-index-card"}}}]);