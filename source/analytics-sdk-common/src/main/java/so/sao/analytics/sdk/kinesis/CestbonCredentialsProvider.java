/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Amazon Software License (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package so.sao.analytics.sdk.kinesis;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import so.sao.analytics.sdk.util.ConfigReader;

/**
 * Provides utilities for retrieving credentials to talk to AWS
 *
 * 
 * @author chengjian.liang, senhui.li
 *
 */
public class CestbonCredentialsProvider {

    public static AWSCredentialsProvider getCredentialsProvider() {

        return new CestbonAWSCredentialsProvider();
    }

    private static class CestbonAWSCredentialsProvider implements AWSCredentialsProvider {

        private ConfigReader configReader = ConfigReader.createOrGetInstance(ConfigReader.AWS_DEF_CONFIG_PATH);

        public AWSCredentials getCredentials() {
            return new BasicAWSCredentials(configReader.getAWSAccessKey(), configReader.getAWSSecretKey());
        }

        public void refresh() {

        }

        @Override
        public String toString() {
            return "CestbonAWSCredentialsProvider";
        }
    }

}
