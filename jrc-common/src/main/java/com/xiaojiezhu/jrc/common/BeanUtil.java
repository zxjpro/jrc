package com.xiaojiezhu.jrc.common;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author xiaojie.zhu
 */
public class BeanUtil {

    public static BeanUtil.ValidateResult validateBean(Object obj) {
        BeanUtil.ValidateResult result = new BeanUtil.ValidateResult();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj, new Class[0]);
        Iterator iter = constraintViolations.iterator();
        while(iter.hasNext()) {
            String message = ((ConstraintViolation)iter.next()).getMessage();
            result.addErrorInfo(message);
        }
        return result;
    }
    public static class ValidateResult {
        private int errNum;
        private String lastErrorInfo;
        private String firstErrorInfo;
        private List<String> errorInfoList;
        private StringBuffer allErrorInfo;
        public ValidateResult() {
        }
        public int getErrorNum() {
            return this.errNum;
        }
        public String getLastErrorInfo() {
            return this.lastErrorInfo;
        }
        public String getFirstErrorInfo() {
            return this.firstErrorInfo;
        }
        public List<String> getErrorList() {
            return this.errorInfoList;
        }
        public String getErrorInfo() {
            return this.allErrorInfo.toString();
        }
        private synchronized void addErrorInfo(String message) {
            if (this.errNum == 0) {
                this.errorInfoList = new ArrayList();
                this.allErrorInfo = new StringBuffer();
                this.firstErrorInfo = message;
                this.allErrorInfo.append(message);
            } else {
                this.allErrorInfo.append(",").append(message);
            }
            this.lastErrorInfo = message;
            this.errorInfoList.add(message);
            ++this.errNum;
        }
    }

}
