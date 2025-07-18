<template>
  <el-dialog v-model="visible" :title="isEditMode ? '编辑领养申请' : '填写领养申请'" width="50%" @close="resetForm">
    <el-form :model="form" :rules="rules" ref="formRef" label-position="top">
      <el-form-item label="您的姓名" prop="adopterName">
        <el-input v-model="form.adopterName" placeholder="请输入您的真实姓名"></el-input>
      </el-form-item>
      <el-form-item label="您的联系电话" prop="adopterPhone">
        <el-input v-model="form.adopterPhone" placeholder="请输入您的联系电话"></el-input>
      </el-form-item>
      <el-form-item label="您的身份证号" prop="adopterIdCard">
        <el-input v-model="form.adopterIdCard" placeholder="请输入您的身份证号码"></el-input>
      </el-form-item>
      <el-form-item label="领养理由" prop="adoptionReason">
        <el-input v-model="form.adoptionReason" type="textarea" :rows="3" placeholder="请简单说明您的领养理由和条件"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="submitAdoption" :loading="loading">
        {{ isEditMode ? '确认修改' : '确认领养' }}
      </el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive, watch, computed } from 'vue';
import { createAdoptionRecord, updateAdoptionRecord } from '@/modules/rescue/api';
import { ElMessage } from 'element-plus';
import { useUserStore } from '@/stores/user';

const props = defineProps({
  animalToAdopt: { type: Object, default: null },
  recordToEdit: { type: Object, default: null },
  modelValue: { type: Boolean, required: true }
});

const emit = defineEmits(['update:modelValue', 'adoption-success']);
const userStore = useUserStore();

const visible = ref(props.modelValue);
const loading = ref(false);
const formRef = ref(null);

const isEditMode = computed(() => !!props.recordToEdit);

const form = reactive({
  adoptionInfoId: null,
  adopterName: '',
  adopterPhone: '',
  adopterIdCard: '',
  adoptionReason: '',
});

const rules = {
  adopterName: [{ required: true, message: '请输入您的姓名', trigger: 'blur' }],
  adopterPhone: [{ required: true, message: '请输入您的联系电话', trigger: 'blur' }],
  adopterIdCard: [{ required: true, message: '请输入您的身份证号', trigger: 'blur' }],
};

watch(() => props.modelValue, (newVal) => {
  visible.value = newVal;
  if (newVal) {
    if (isEditMode.value) {
      Object.assign(form, props.recordToEdit);
    } else {
      form.adoptionInfoId = props.animalToAdopt?.id;
    }
  }
});

watch(visible, (newVal) => {
  emit('update:modelValue', newVal);
});

const resetForm = () => {
  if (formRef.value) {
    formRef.value.resetFields();
  }
  form.adoptionInfoId = null;
};

const submitAdoption = async () => {
  if (!formRef.value || !userStore.isAuthenticated) return;
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      loading.value = true;
      try {
        if (isEditMode.value) {
          const { id, animalInfo, ...updateData } = form;
          await updateAdoptionRecord(id, updateData);
          ElMessage.success('修改成功！');
        } else {
          await createAdoptionRecord(form);
          ElMessage.success('领养申请提交成功！');
        }
        emit('adoption-success', form.adoptionInfoId);
        visible.value = false;
      } catch (err) {
        ElMessage.error(err.message || '网络请求失败');
      } finally {
        loading.value = false;
      }
    }
  });
};
</script> 