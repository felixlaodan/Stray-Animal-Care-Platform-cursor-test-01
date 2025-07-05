<template>
  <el-dialog 
    v-model="visible" 
    :title="isEditMode ? '编辑用户' : '新增用户'" 
    width="500px" 
    @close="resetForm"
  >
    <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username" :disabled="isEditMode"></el-input>
      </el-form-item>
      <el-form-item label="昵称" prop="nickname">
        <el-input v-model="form.nickname"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" type="password" :placeholder="isEditMode ? '留空则不修改' : '请输入密码'"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input v-model="form.confirmPassword" type="password" placeholder="请再次输入密码"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary" @click="submitForm">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive, watch, computed } from 'vue';
import { adminCreateUser, adminUpdateUser } from '@/modules/user/api';
import { ElMessage } from 'element-plus';

const props = defineProps({
  modelValue: { type: Boolean, required: true },
  userToEdit: { type: Object, default: null }
});

const emit = defineEmits(['update:modelValue', 'submit-success']);

const visible = ref(props.modelValue);
const formRef = ref(null);

const isEditMode = computed(() => !!props.userToEdit);

const form = reactive({
  username: '',
  nickname: '',
  password: '',
  confirmPassword: ''
});

const validatePassword = (rule, value, callback) => {
  if (isEditMode.value && !value) {
    // 编辑模式下，如果密码为空，则通过校验
    if (form.confirmPassword) {
        formRef.value.validateField('confirmPassword', () => {});
    }
    callback();
  } else if (!value) {
    callback(new Error('请输入密码'));
  } else {
    if (form.confirmPassword) {
        formRef.value.validateField('confirmPassword', () => {});
    }
    callback();
  }
};

const validateConfirmPass = (rule, value, callback) => {
  if (value && value !== form.password) {
    callback(new Error("两次输入的密码不一致!"));
  } else if (!value && form.password) {
    callback(new Error('请再次输入密码'));
  } else {
    callback();
  }
};

const rules = reactive({
  username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
  nickname: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
  password: [{ validator: validatePassword, trigger: 'blur' }],
  confirmPassword: [{ validator: validateConfirmPass, trigger: 'blur' }]
});

watch(() => props.modelValue, (newVal) => {
  visible.value = newVal;
  if (newVal && isEditMode.value) {
    Object.assign(form, props.userToEdit);
    form.password = ''; // 编辑时清空密码相关字段
    form.confirmPassword = '';
  }
});

watch(visible, (newVal) => {
  emit('update:modelValue', newVal);
});

const resetForm = () => {
  if(formRef.value) {
    formRef.value.resetFields();
  }
};

const submitForm = async () => {
  if (!formRef.value) return;

  await formRef.value.validate(async (valid) => {
    if (valid) {
      // 准备提交的数据
      const dataToSubmit = {
        username: form.username,
        nickname: form.nickname,
        password: form.password,
      };
      
      // 如果是编辑模式且密码为空，则不提交密码字段
      if (isEditMode.value && !dataToSubmit.password) {
        delete dataToSubmit.password;
      }

      try {
        if (isEditMode.value) {
          // 调用更新接口
          await adminUpdateUser(props.userToEdit.id, dataToSubmit);
        } else {
          // 调用创建接口
          await adminCreateUser(dataToSubmit);
        }
        ElMessage.success(`${isEditMode.value ? '更新' : '创建'}成功！`);
        emit('submit-success');
        visible.value = false;
      } catch (error) {
        ElMessage.error(error.message || '操作失败');
      }
    }
  });
};
</script> 