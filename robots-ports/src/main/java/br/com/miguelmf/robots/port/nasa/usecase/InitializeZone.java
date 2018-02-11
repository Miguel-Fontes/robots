package br.com.miguelmf.robots.port.nasa.usecase;

/**
 * InitializeZone defines the contract of the zone initialization
 * contract, making it possible for the client to control Zone's parameters
 * like it's Dimensions
 *
 * @author Miguel Fontes
 */
public interface InitializeZone {

    /**
     * Initialize the simulation Zone using default parameters:
     *
     * <ul>
     *     <li>Dimensions 5x5</li>
     *     <li>One Robot at Position (0,0) facing North</li>
     * </ul>
     */
    void initilize();

}
