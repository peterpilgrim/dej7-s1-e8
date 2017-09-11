/*******************************************************************************
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2017 by Peter Pilgrim, Milton Keynes, P.E.A.T UK LTD
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Creative Commons 3.0
 * Non Commercial Non Derivation Share-alike License
 * https://creativecommons.org/licenses/by-nc-nd/4.0/
 *
 * Developers:
 * Peter Pilgrim -- design, development and implementation
 *               -- Blog: http://www.xenonique.co.uk/blog/
 *               -- Twitter: @peter_pilgrim
 *
 * Contributors:
 *
 *******************************************************************************/
package uk.co.xenonique.training.stadia;


import fish.payara.micro.PayaraMicro;
import fish.payara.micro.BootstrapException;
import fish.payara.micro.PayaraMicroRuntime;

/**
 * The type PayaraEmbeddedApplication
 *
 * @author Peter Pilgrim
 */
public class PayaraEmbeddedApplication {
    public static void main(String[] args) throws BootstrapException {
        final PayaraMicroRuntime runtime = PayaraMicro.getInstance().getRuntime();
        runtime.run("healthcheck-configure", "--enabled=true", "--dynamic=true");

        runtime.run("healthcheck-configure-service", "--serviceName=healthcheck-cpu", "--enabled=true",
                "--time=3", "--unit=SECONDS", "--dynamic=true");
        runtime.run("healthcheck-configure-service-threshold", "--serviceName=healthcheck-cpu",
                "--thresholdCritical=90", "--thresholdWarning=50", "--thresholdGood=0", "--dynamic=true");
        runtime.run("healthcheck-configure-service", "--serviceName=healthcheck-machinemem",
                "--enabled=true", "--dynamic=true", "--time=3","--unit=SECONDS");
        runtime.run("healthcheck-configure-service-threshold", "--serviceName=healthcheck-machinemem",
                "--thresholdCritical=90", "--thresholdWarning=50", "--thresholdGood=0", "--dynamic=true");

    }
}
