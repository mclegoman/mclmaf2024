package com.mclegoman.mclmaf2024.client.entity.renderer.feature;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.RotationAxis;

public class MoobloomFlowerFeatureRenderer<MoobloomEntity extends LivingEntity, M extends EntityModel<MoobloomEntity>> extends FeatureRenderer<MoobloomEntity, M> {
	private final BlockRenderManager blockRenderManager;
	public MoobloomFlowerFeatureRenderer(FeatureRendererContext<MoobloomEntity, M> context, BlockRenderManager blockRenderManager) {
		super(context);
		this.blockRenderManager = blockRenderManager;
	}
	public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, MoobloomEntity moobloomEntity, float f, float g, float h, float j, float k, float l) {
		if (!moobloomEntity.isBaby()) {
			MinecraftClient minecraftClient = MinecraftClient.getInstance();
			boolean bl = minecraftClient.hasOutline(moobloomEntity) && moobloomEntity.isInvisible();
			if (!moobloomEntity.isInvisible() || bl) {
				BlockState blockState = ((com.mclegoman.mclmaf2024.common.entity.MoobloomEntity)moobloomEntity).getFlower().getDefaultState();
				int m = LivingEntityRenderer.getOverlay(moobloomEntity, 0.0F);
				BakedModel bakedModel = this.blockRenderManager.getModel(blockState);
				matrixStack.push();
				matrixStack.translate(0.2F, -0.35F, 0.5F);
				matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0F));
				matrixStack.scale(-1.0F, -1.0F, 1.0F);
				matrixStack.translate(-0.5F, -0.5F, -0.5F);
				this.renderFlower(matrixStack, vertexConsumerProvider, i, bl, blockState, m, bakedModel);
				matrixStack.pop();
				matrixStack.push();
				matrixStack.translate(0.2F, -0.35F, 0.5F);
				matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(42.0F));
				matrixStack.translate(0.1F, 0.0F, -0.6F);
				matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-48.0F));
				matrixStack.scale(-1.0F, -1.0F, 1.0F);
				matrixStack.translate(-0.5F, -0.5F, -0.5F);
				this.renderFlower(matrixStack, vertexConsumerProvider, i, bl, blockState, m, bakedModel);
				matrixStack.pop();
				matrixStack.push();
				((CowEntityModel<?>)this.getContextModel()).getHead().rotate(matrixStack);
				matrixStack.translate(0.0F, -0.7F, -0.2F);
				matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-78.0F));
				matrixStack.scale(-1.0F, -1.0F, 1.0F);
				matrixStack.translate(-0.5F, -0.5F, -0.5F);
				this.renderFlower(matrixStack, vertexConsumerProvider, i, bl, blockState, m, bakedModel);
				matrixStack.pop();
			}
		}
	}
	private void renderFlower(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean renderAsModel, BlockState flower, int overlay, BakedModel model) {
		if (renderAsModel) {
			this.blockRenderManager.getModelRenderer().render(matrices.peek(), vertexConsumers.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)), flower, model, 0.0F, 0.0F, 0.0F, light, overlay);
		} else {
			this.blockRenderManager.renderBlockAsEntity(flower, matrices, vertexConsumers, light, overlay);
		}

	}
}