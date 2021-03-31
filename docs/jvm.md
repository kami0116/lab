### 1. class文件结构

jvm8 oracle官方地址：https://docs.oracle.com/javase/specs/jvms/se8/html/index.html

```
ClassFile {
    u4             magic;
    u2             minor_version;
    u2             major_version;
    u2             constant_pool_count;
    cp_info        constant_pool[constant_pool_count-1];
    u2             access_flags;
    u2             this_class;
    u2             super_class;
    u2             interfaces_count;
    u2             interfaces[interfaces_count];
    u2             fields_count;
    field_info     fields[fields_count];
    u2             methods_count;
    method_info    methods[methods_count];
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
```

---

#### 1.1 The Constant Pool (cp_info)

<table>
	<caption>常量池所有类型结构汇总</caption>
	<tr>
		<td rowspan="2">CONSTANT_Class_info </td>
		<td>1</td>
		<td>2</td>
		<td>-</td>
	</tr>
	<tr>
		<td>tag = 7</td>
		<td>name_index</td>
		<td>-</td>
	</tr>
    <tr>
		<td rowspan="2">CONSTANT_Fieldref_info</td>
		<td>1</td>
		<td>2</td>
		<td>2</td>
	</tr>
	<tr>
		<td>tag = 9</td>
		<td>class_index</td>
		<td>name_and_type_index</td>
	</tr>
	<tr>
		<td rowspan="2">CONSTANT_Methodref_info</td>
		<td>1</td>
		<td>2</td>
		<td>2</td>
	</tr>
	<tr>
		<td>tag = 10</td>
		<td>class_index</td>
		<td>name_and_type_index</td>
	</tr>
	<tr>
		<td rowspan="2">CONSTANT_InterfaceMethodref_info</td>
		<td>1</td>
		<td>2</td>
		<td>2</td>
	</tr>
	<tr>
		<td>tag = 11</td>
		<td>class_index</td>
		<td>name_and_type_index</td>
	</tr>
	<tr>
		<td rowspan="2">CONSTANT_String_info</td>
		<td>1</td>
		<td>2</td>
		<td>-</td>
	</tr>
	<tr>
		<td>tag = 8</td>
		<td>string_index</td>
		<td>-</td>
	</tr>
	<tr>
		<td rowspan="2">CONSTANT_Integer_info</td>
		<td>1</td>
		<td>4</td>
		<td>-</td>
	</tr>
	<tr>
		<td>tag = 3</td>
		<td>bytes</td>
		<td>-</td>
	</tr>
	<tr>
		<td rowspan="2">CONSTANT_Float_info</td>
		<td>1</td>
		<td>4</td>
		<td>-</td>
	</tr>
	<tr>
		<td>tag = 4</td>
		<td>bytes</td>
		<td>-</td>
	</tr>
	<tr>
		<td rowspan="2">CONSTANT_Long_info</td>
		<td>1</td>
		<td>4</td>
		<td>4</td>
	</tr>
	<tr>
		<td>tag = 5</td>
		<td>high_bytes</td>
		<td>low_bytes</td>
	</tr>
	<tr>
		<td rowspan="2">CONSTANT_Double_info</td>
		<td>1</td>
		<td>4</td>
		<td>4</td>
	</tr>
	<tr>
		<td>tag = 6</td>
		<td>high_bytes</td>
		<td>low_bytes</td>
	</tr>
	<tr>
		<td rowspan="2">CONSTANT_NameAndType_info</td>
		<td>1</td>
		<td>2</td>
		<td>2</td>
	</tr>
	<tr>
		<td>tag = 12</td>
		<td>name_index</td>
		<td>descriptor_index</td>
	</tr>
	<tr>
		<td rowspan="2">CONSTANT_Utf8_info</td>
		<td>1</td>
		<td>2</td>
		<td>1</td>
	</tr>
	<tr>
		<td>tag = 1</td>
		<td>length</td>
		<td>bytes[length]</td>
	</tr>
	<tr>
		<td rowspan="2">CONSTANT_MethodHandle_info</td>
		<td>1</td>
		<td>1</td>
		<td>2</td>
	</tr>
	<tr>
		<td>tag = 15</td>
		<td>reference_kind</td>
		<td>reference_index</td>
	</tr>
    <tr>
		<td rowspan="2">CONSTANT_MethodType_info</td>
		<td>1</td>
		<td>2</td>
		<td>-</td>
	</tr>
	<tr>
		<td>tag = 16</td>
		<td>descriptor_index</td>
		<td>-</td>
	</tr>
	<tr>
		<td rowspan="2">CONSTANT_InvokeDynamic_info</td>
		<td>1</td>
		<td>2</td>
		<td>2</td>
	</tr>
	<tr>
		<td>tag = 18</td>
		<td>bootstrap_method_attr_index</td>
		<td>name_and_type_index</td>
	</tr>
</table>

#### 1.2 Fields

```
field_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
```

#### 1.3 methods

```
method_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
```

#### 1.5 attributes

```
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
```