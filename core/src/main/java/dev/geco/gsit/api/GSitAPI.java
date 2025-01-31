package dev.geco.gsit.api;

import dev.geco.gsit.GSitMain;
import dev.geco.gsit.objects.GSeat;
import dev.geco.gsit.objects.GetUpReason;
import dev.geco.gsit.objects.IGCrawl;
import dev.geco.gsit.objects.IGPose;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class GSitAPI {

    /**
     * Returns the plugin instance for GSit
     * @author Gecolay
     * @since 1.0.0
     * @return plugin instance
     */
    public static @NotNull GSitMain getInstance() {
        return GSitMain.getInstance();
    }

    /**
     * Checks if an entity can sit by click
     * @author Gecolay
     * @since 1.0.0
     * @param entity Entity
     * @return <code>true</code> if the entity can sit by click
     */
    public static boolean canEntityUseSit(@NotNull Entity entity) {
        return getInstance().getToggleService().canEntityUseSit(entity.getUniqueId());
    }

    /**
     * Sets if an entity can sit by click
     * @author Gecolay
     * @since 1.0.0
     * @param entity Entity
     * @param canUseSit Can entity sit
     */
    public static void setEntityCanUseSit(@NotNull Entity entity, boolean canUseSit) {
        getInstance().getToggleService().setEntityCanUseSit(entity.getUniqueId(), canUseSit);
    }

    /**
     * Checks if an entity is currently sitting
     * @author Gecolay
     * @since 1.0.0
     * @param entity Entity
     * @return <code>true</code> if the entity is sitting
     */
    public static boolean isEntitySitting(@NotNull LivingEntity entity) {
        return getInstance().getSitService().isEntitySitting(entity);
    }

    /**
     * Gets all seat objects
     * @author Gecolay
     * @since 1.0.0
     * @return List of all seat objects
     */
    public static @NotNull List<GSeat> getAllSeats() {
        return getInstance().getSitService().getAllSeats();
    }

    /**
     * Gets the seat object of an entity
     * @author Gecolay
     * @since 1.0.0
     * @param entity Entity
     * @return Seat object or <code>null</code> if there was no seat object
     */
    public static @Nullable GSeat getSeatByEntity(@NotNull LivingEntity entity) {
        return getInstance().getSitService().getSeatByEntity(entity);
    }

    /**
     * Gets all seat objects by a block
     * @author Gecolay
     * @since 1.0.0
     * @param block Block
     * @return List of seat objects
     */
    public static @NotNull List<GSeat> getSeatsByBlock(@NotNull Block block) {
        return getInstance().getSitService().getSeatsByBlock(block);
    }

    /**
     * Gets all seat objects by a list of blocks
     * @author Gecolay
     * @since 1.0.0
     * @param blocks Blocks
     * @return List of seat objects
     */
    public static @NotNull List<GSeat> getSeatsByBlocks(@NotNull List<Block> blocks) {
        return getInstance().getSitService().getSeatsByBlocks(blocks);
    }

    /**
     * Creates a new seat object on a block for an entity
     * @author Gecolay
     * @since 1.0.0
     * @param block Block which should be connected to the returned seat object
     * @param entity Entity
     * @return Seat object or <code>null</code> if the creation failed or was cancelled by event
     */
    public static @Nullable GSeat createSeat(@NotNull Block block, @NotNull LivingEntity entity) {
        return getInstance().getSitService().createSeat(block, entity);
    }

    /**
     * Creates a new seat object on a block for an entity
     * The seat can be static or rotating
     * @author Gecolay
     * @since 1.0.0
     * @param block Block which should be connected to the returned seat object
     * @param entity Entity
     * @param canRotate Can the seat rotate
     * @param seatRotation The default rotation of the seat
     * @param sitInBlockCenter Should the seat location be centered on the block
     * @return Seat object or <code>null</code> if the creation failed or was cancelled by event
     */
    public static @Nullable GSeat createSeat(@NotNull Block block, @NotNull LivingEntity entity, boolean canRotate, float seatRotation, boolean sitInBlockCenter) {
        return createSeat(block, entity, canRotate, 0d, 0d, 0d, seatRotation, sitInBlockCenter);
    }

    /**
     * Creates a new seat object on a block for an entity
     * The seat can be static or rotating
     * The seat can be moved to with an offset
     * @author Gecolay
     * @since 1.0.0
     * @param block Block which should be connected to the returned seat object
     * @param entity Entity
     * @param canRotate Can the seat rotate
     * @param xOffset The x coordinate offset for the seat
     * @param yOffset The y coordinate offset for the seat
     * @param zOffset The z coordinate offset for the seat
     * @param seatRotation The default rotation of the seat
     * @param sitInBlockCenter Should the seat location be centered on the block
     * @return Seat object or <code>null</code> if the creation failed or was cancelled by event
     */
    public static @Nullable GSeat createSeat(@NotNull Block block, @NotNull LivingEntity entity, boolean canRotate, double xOffset, double yOffset, double zOffset, float seatRotation, boolean sitInBlockCenter) {
        return getInstance().getSitService().createSeat(block, entity, canRotate, xOffset, yOffset, zOffset, seatRotation, sitInBlockCenter);
    }

    /**
     * Moves an entity on a seat object in a block direction
     * @author Gecolay
     * @since 1.0.4
     * @param entity Entity
     * @param blockDirection The direction in which the seat object should get moved
     */
    public static void moveSeat(@NotNull LivingEntity entity, @NotNull BlockFace blockDirection) {
        getInstance().getSitService().moveSeat(entity, blockDirection);
    }

    /**
     * Removes a seat object for an entity
     * @author Gecolay
     * @since 1.0.0
     * @param entity Entity
     * @param getUpReason The reason why the seat object gets removed
     * @return <code>true</code> or <code>false</code> if the deletion failed or was cancelled by event
     */
    public static boolean removeSeat(@NotNull LivingEntity entity, @NotNull GetUpReason getUpReason) {
        return getInstance().getSitService().removeSeat(entity, getUpReason);
    }

    /**
     * Removes a seat object for an entity
     * The return teleport can be disabled, if for example the entity already teleports somewhere else
     * @author Gecolay
     * @since 1.0.0
     * @param entity Entity
     * @param getUpReason The reason why the seat object gets removed
     * @param useReturnLocation Should the entity get teleported to the return location
     * @return <code>true</code> or <code>false</code> if the deletion failed or was cancelled by event
     */
    public static boolean removeSeat(@NotNull LivingEntity entity, @NotNull GetUpReason getUpReason, boolean useReturnLocation) {
        return getInstance().getSitService().removeSeat(entity, getUpReason, useReturnLocation);
    }

    /**
     * Checks if a player can sit on another player by click
     * @author Gecolay
     * @since 1.0.0
     * @param playerUuid UUID of the player
     * @return <code>true</code> if the player can sit on another player
     */
    public static boolean canPlayerUsePlayerSit(@NotNull UUID playerUuid) {
        return getInstance().getToggleService().canPlayerUsePlayerSit(playerUuid);
    }

    /**
     * Sets if a player can sit on another player by click
     * @author Gecolay
     * @since 1.0.0
     * @param playerUuid UUID of the player
     * @param canUsePlayerSit Should the player be able to sit on another player by click
     */
    public static void setPlayerCanUsePlayerSit(@NotNull UUID playerUuid, boolean canUsePlayerSit) {
        getInstance().getToggleService().setPlayerCanUsePlayerSit(playerUuid, canUsePlayerSit);
    }

    /**
     * Places a player on top of another target player
     * @author Gecolay
     * @since 1.0.0
     * @param player Player
     * @param target Target player
     * @return <code>true</code> or <code>false</code> if the action was cancelled by event
     */
    public static boolean sitOnPlayer(@NotNull Player player, @NotNull Player target) {
        return getInstance().getPlayerSitService().sitOnPlayer(player, target);
    }

    /**
     * Stops a player from sitting on another player
     * @author Gecolay
     * @since 1.0.0
     * @param entity Entity
     * @param getUpReason The reason why the player gets off
     * @return <code>true</code> or <code>false</code> if the action was cancelled by event
     */
    public static boolean stopPlayerSit(@NotNull Entity entity, @NotNull GetUpReason getUpReason) {
        return getInstance().getPlayerSitService().stopPlayerSit(entity, getUpReason);
    }

    /**
     * Checks if a player is currently posing
     * @author Gecolay
     * @since 1.0.0
     * @param player Player
     * @return <code>true</code> if the player is posing
     */
    public static boolean isPlayerPosing(@NotNull Player player) {
        return getInstance().getPoseService().isPlayerPosing(player);
    }

    /**
     * Gets all pose objects
     * @author Gecolay
     * @since 1.0.0
     * @return List of all pose objects
     */
    public static @NotNull List<IGPose> getAllPoses() {
        return getInstance().getPoseService().getAllPoses();
    }

    /**
     * Gets the pose object of a player
     * @author Gecolay
     * @since 1.0.0
     * @param player Player
     * @return Pose object or <code>null</code> if there was no pose object
     */
    public static @Nullable IGPose getPoseByPlayer(@NotNull Player player) {
        return getInstance().getPoseService().getPoseByPlayer(player);
    }

    /**
     * Gets all pose objects by a block
     * @author Gecolay
     * @since 1.0.0
     * @param block Block
     * @return List of pose objects
     */
    public static @NotNull List<IGPose> getPosesByBlock(@NotNull Block block) {
        return getInstance().getPoseService().getPosesByBlock(block);
    }

    /**
     * Gets all pose objects by a list of blocks
     * @author Gecolay
     * @since 1.0.0
     * @param blocks Blocks
     * @return List of pose objects
     */
    public static @NotNull List<IGPose> getPosesByBlocks(@NotNull List<Block> blocks) {
        return getInstance().getPoseService().getPosesByBlocks(blocks);
    }

    /**
     * Creates a new pose object on a block for a player
     * @author Gecolay
     * @since 1.0.0
     * @param block Block which should be connected to the returned pose object
     * @param player Player
     * @param pose Pose {@link Pose}
     * @return Pose object or <code>null</code> if the creation failed or was cancelled by event
     */
    public static @Nullable IGPose createPose(@NotNull Block block, @NotNull Player player, @NotNull Pose pose) {
        return getInstance().getPoseService().createPose(block, player, pose);
    }

    /**
     * Creates a new pose object on a block for a player
     * The pose can be static or rotating
     * @author Gecolay
     * @since 1.0.0
     * @param block Block which should be connected to the returned pose object
     * @param player Player
     * @param pose Pose {@link Pose}
     * @param seatRotation The default rotation of the seat from the pose
     * @param sitInBlockCenter Should the seat location from the pose be centered on the block
     * @return Pose object or <code>null</code> if the creation failed or was cancelled by event
     */
    public static @Nullable IGPose createPose(@NotNull Block block, @NotNull Player player, @NotNull Pose pose, float seatRotation, boolean sitInBlockCenter) {
        return createPose(block, player, pose, 0d, 0d, 0d, seatRotation, sitInBlockCenter);
    }

    /**
     * Creates a new pose object on a block for a player
     * The pose can be static or rotating
     * The pose can be moved to with an offset
     * @author Gecolay
     * @since 1.0.0
     * @param block Block which should be connected to the returned pose object
     * @param player Player
     * @param pose Pose {@link Pose}
     * @param xOffset The x coordinate offset for the pose
     * @param yOffset The y coordinate offset for the pose
     * @param zOffset The z coordinate offset for the pose
     * @param seatRotation The default rotation of the seat from the pose
     * @param sitInBlockCenter Should the seat location from the pose be centered on the block
     * @return Pose object or <code>null</code> if the creation failed or was cancelled by event
     */
    public static @Nullable IGPose createPose(@NotNull Block block, @NotNull Player player, @NotNull Pose pose, double xOffset, double yOffset, double zOffset, float seatRotation, boolean sitInBlockCenter) {
        return getInstance().getPoseService().createPose(block, player, pose, xOffset, yOffset, zOffset, seatRotation, sitInBlockCenter);
    }

    /**
     * Removes a pose object for a player
     * @author Gecolay
     * @since 1.0.0
     * @param player Player
     * @param getUpReason The reason why the pose object gets removed
     * @return <code>true</code> or <code>false</code> if the deletion failed or was cancelled by event
     */
    public static boolean removePose(@NotNull Player player, @NotNull GetUpReason getUpReason) {
        return getInstance().getPoseService().removePose(player, getUpReason);
    }

    /**
     * Removes a pose object for a player
     * The return teleport can be disabled, if for example the entity already teleports somewhere else
     * @author Gecolay
     * @since 1.0.0
     * @param player Player
     * @param getUpReason The reason why the pose object gets removed
     * @param useReturnLocation Should the entity get teleported to the return location
     * @return <code>true</code> or <code>false</code> if the deletion failed or was cancelled by event
     */
    public static boolean removePose(@NotNull Player player, @NotNull GetUpReason getUpReason, boolean useReturnLocation) {
        return getInstance().getPoseService().removePose(player, getUpReason, useReturnLocation);
    }

    /**
     * Checks if a player is currently crawling
     * @author Gecolay
     * @since 1.0.0
     * @param player Player
     * @return <code>true</code> if the player is crawling
     */
    public static boolean isPlayerCrawling(@NotNull Player player) {
        return getInstance().getCrawlService().isPlayerCrawling(player);
    }

    /**
     * Gets all crawl objects
     * @author Gecolay
     * @since 1.0.0
     * @return List of all crawl objects
     */
    public static @NotNull List<IGCrawl> getAllCrawls() {
        return getInstance().getCrawlService().getAllCrawls();
    }

    /**
     * Gets the crawl object by a player
     * @author Gecolay
     * @since 1.0.0
     * @param player Player
     * @return Crawl object or <code>null</code> if there was no crawl object
     */
    public static @Nullable IGCrawl getCrawlByPlayer(@NotNull Player player) {
        return getInstance().getCrawlService().getCrawlByPlayer(player);
    }

    /**
     * Starts a new crawl session for a player
     * @author Gecolay
     * @since 1.0.0
     * @param player Player
     * @return Crawl object or <code>null</code> if the start failed or was cancelled by event
     */
    public static @Nullable IGCrawl startCrawl(@NotNull Player player) {
        return getInstance().getCrawlService().startCrawl(player);
    }

    /**
     * Stops a crawl session for a player
     * @author Gecolay
     * @since 1.0.0
     * @param player Player
     * @param getUpReason  The reason why the crawl session stops
     * @return <code>true</code> or <code>false</code> if the stop failed or was cancelled by event
     */
    public static boolean stopCrawl(@NotNull Player player, @NotNull GetUpReason getUpReason) {
        return getInstance().getCrawlService().stopCrawl(player, getUpReason);
    }

}