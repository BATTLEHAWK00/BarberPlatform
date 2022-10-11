import { StepsForm } from '@ant-design/pro-components';
import React from 'react';
import CreateCustomerOrderStep1 from './step1';
import CreateCustomerOrderStep2 from './step2';
const CreateCustomerOrder: React.FC = () => {
  return (
    <>
      <StepsForm>
        <CreateCustomerOrderStep1 />
        <CreateCustomerOrderStep2 />
      </StepsForm>
    </>
  );
};

export default CreateCustomerOrder;
